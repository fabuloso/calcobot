(ns calcobot.core
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]
            [calcobot.config :as config]
            [calcobot.api :as api]
            [cheshire.core :refer [parse-string]]
            [clojure.walk :refer [keywordize-keys]]) 
  (:gen-class))

(defn url [] (str 
            (config/get-url )
            (config/get-token )))

(defn get-updates []
  (client/get (str (url) "/getUpdates")))

(defn get-chat-id-from-updates []
  (get-chat-id
   (parse-update
    (get-updates))))

(defn send-msg [chat-id msg]
  (client/get
   (str (url) "/sendMessage?chat_id=" chat-id " &text=" msg)))

(defn read-and-send []
  (send-msg
   (get-chat-id-from-updates)
   (get-message (parse-update (get-updates)))))

(defn -main []
  (println (config/get-token)))

