(ns re-board.events
  (:require [re-frame.core :as re-frame]
            [re-board.db :as db]
            [ajax.core :as ajax]))

(re-frame/reg-event-db
 ::initialize-db
 (fn  [_ _]
   db/default-db))

(defn set-active-panel [db [_ active-panel]]
  (assoc db :active-panel active-panel))

(re-frame/reg-event-db
 ::set-active-panel
 set-active-panel)


(defn change-name [{:keys [db]} [_ name]]
  {:db (assoc db :name name)})

(re-frame/reg-event-fx
 ::change-name
 change-name)

(defn fetch-games
  [{:keys [db]} _]
  {:http-xhrio {:method :get
                :uri "https://bgg-json.azurewebsites.net/hot"
                :timeout 8000
                :response-format (ajax/json-response-format {:keywords? true})
                :on-success [::update-games]
                :on-failure [::error-games]}})

(re-frame/reg-event-fx ::fetch-games fetch-games)


(defn update-games
  [{:keys [db]} [_ data]]
  {:db (assoc db :games data)})

(re-frame/reg-event-fx ::update-games update-games)
