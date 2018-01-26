(ns re-board.subs
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 ::active-panel
 (fn [db _]
   (:active-panel db)))

(defn games
  [db _]
  (:games db))

(re-frame/reg-sub ::games games)

(defn game-db
  [games _]
  (->> games
       (map (fn [game] [(:gameId game) game]))
       (into {})))

(re-frame/reg-sub ::game-db
                  :<- [::games]
                  game-db)

(defn game-ids
  [game-db _]
  (keys game-db))

(re-frame/reg-sub
 ::game-ids
 :<- [::game-db]
 game-ids)

(defn game-data
  [game-db [_ id]]
  (get game-db id))

(re-frame/reg-sub
 ::game-data
 :<- [::game-db]
 game-data)
