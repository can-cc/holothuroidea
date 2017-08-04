(ns holothuroidea.parse
  (:require [cljs.nodejs :as node]
            [holothuroidea.util :as util]
            [cljs.core.async]
            [promesa.core :as p]
            [promesa.async-cljs :refer-macros [async]]))

(def experience-file=extension "experience-file=extension" "md")

(defn take-file-body-name
  [path]
  (nth (re-find #"([\s\S]+/)([A-Za-z0-9-_\s]+)(.md$)" path) 2))

(defn parse-experience-file [file-path]
  (p/alet [filename (take-file-body-name file-path)]
          [file-content (p/await (util/read-file file-path))])
  {:filename filename
   :experience file-content}
  )
