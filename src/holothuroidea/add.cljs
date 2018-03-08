(ns holothuroidea.add
  (:require [cljs.nodejs :as node]
            [clojure.string :as string]
            ))

(def fs (node/require "fs"))
(def moment (node/require "moment"))

(defn add-experience [rest]
  (let [filename (first rest)
        filepath (str filename ".md")
        title (or (second rest) filename)]
    (if (.existsSync fs filepath)
      (println (str filepath " already exist."))
      (do
       (->> (.openSync fs filepath, "w")
            (.closeSync fs))
       (.writeFileSync
        fs
        filepath
        (string/join "\n"
                     [(str "--title:" title)
                      (str "--date:" (.format (moment)))
                      "--tag:"
                      "###"]))))))
