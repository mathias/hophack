(set-env!
  :source-paths   #{"src"}
  :resource-paths #{"html"}
  :dependencies '[
    [adzerk/boot-cljs            "0.0-3308-0"      :scope "test"]
    [adzerk/boot-cljs-repl       "0.1.10-SNAPSHOT" :scope "test"]
    [hoplon/boot-hoplon          "0.1.7"           :scope "test"]
    [pandeiro/boot-http          "0.6.3"           :scope "test"]
    [crisptrutski/boot-cljs-test "0.1.0-SNAPSHOT"  :scope "test"]
    [org.clojure/clojure         "1.7.0"]
    [org.clojure/clojurescript   "1.7.58"]
    [hoplon                      "6.0.0-alpha9"]])

(require
  '[adzerk.boot-cljs      :refer [cljs]]
  '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
  '[hoplon.boot-hoplon    :refer [hoplon]]
  '[crisptrutski.boot-cljs-test  :refer [test-cljs]]
  '[pandeiro.boot-http    :refer [serve]])

(deftask auto-test []
  (set-env! :source-paths #{"src" "test"})
  (comp (watch)
        (speak)
        (test-cljs)))

(deftask dev []
  (set-env! :source-paths #{"src"})
  (comp (serve :dir "target/")
        (watch)
;;        (speak)
        (hoplon)
;;        (cljs-repl)
        (cljs :source-map true :optimizations :none)))
