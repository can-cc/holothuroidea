(require '[cljs.build.api :as b])

(b/watch "src"
  {:main 'holothuroidea.core
   :output-to "out/holothuroidea.js"
   :output-dir "out"
   :target :nodejs
   :verbose true
   :cache-analysis true
   :pretty-print true})
