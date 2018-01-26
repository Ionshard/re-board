(ns re-board.views
  (:require [re-frame.core :as re-frame]
            [re-board.subs :as subs]
            [re-board.events :as events]
            [soda-ash.core :as sa]))


;; home


(defn game-card [game-id]
  (let [game @(re-frame/subscribe [::subs/game-data game-id])]
    [sa/Card
     [sa/Image {:src (:thumbnail game)}]
     [sa/CardContent
      [sa/CardHeader (:name game)]]]))

(defn games-group []
  (let [game-ids @(re-frame/subscribe [::subs/game-ids])]
    (into [sa/CardGroup]
          (map game-card game-ids))))

(defn home-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div (str "Hello from " @name ". This is the Home Page.")
     [games-group]
     [:button {:onClick #(re-frame/dispatch [::events/fetch-games])}
      "Fetch Games"]
     [:div [:a {:href "#/about"} "go to About Page"]]]))


;; about

(defn about-panel []
  [:div "This is the About Page."
   [:div [:a {:href "#/"} "go to Home Page"]]])


;; main

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [show-panel @active-panel]))
