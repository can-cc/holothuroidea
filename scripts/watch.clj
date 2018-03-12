(require '[cljs.build.api :as b])

(b/watch "src"
  {:main 'holothuroidea.core
   :output-to "out/holothuroidea.js"
   :output-dir "out"
   :optimizations :simple
   :target :nodejs
   :verbose true
   :foreign-libs [{:file "src"
                   :module-type :es6}] ;; or :commonjs / :amd
   :cache-analysis true
   :pretty-print true})
