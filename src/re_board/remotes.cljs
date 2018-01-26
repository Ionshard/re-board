(ns re-board.remotes
  (:require [ajax.core :as ajax]))


(def api-url "https://bgg-json.azurewebsites.net/")

(defn hot
  [on-success]
  {:method :get
   :uri (str api-url "hot")
   :response-format (ajax/json-response-format {:keywords? true})
   :on-success on-success})

