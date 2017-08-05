(require '[cljs.build.api :as b])

(b/watch "src"
  {:main 'holothuroidea.core
   :output-to "out/holothuroidea.js"
   :output-dir "out"
   :target :nodejs
   :verbose true
   :foreign-libs [{:file "src"
                   :module-type :es6}] ;; or :commonjs / :amd
   :pretty-print true})
