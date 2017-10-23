(ns re-board.utils)

(defn parse-json
  [json-str]
  (-> (js/JSON.parse json-str)
      (js->clj :keywordize-keys true)))
