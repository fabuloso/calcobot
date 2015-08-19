(ns calcobot.core
  (:require [clj-http.client :as client]
            [calcobot.config :as config]
            [calcobot.telegram-api :as api]
            [overtone.at-at :as at])
  (:gen-class))

(defn url [] (str
              (config/get-url )
              (config/get-token )))

(defn get-updates []
  (client/get (str (url) "/getUpdates")))

(defn get-chat-id-from-updates []
  (api/get-chat-id
   (api/parse-update
    (get-updates))))

(defn send-msg [chat-id msg]
  (client/get
   (str (url) "/sendMessage?chat_id=" chat-id " &text=" msg)))

(defn read-and-send []
  (send-msg
   (get-chat-id-from-updates)
   (api/get-message (api/parse-update (get-updates)))))

(defn set-interval [callback ms]
  (future (while true (do (Thread/sleep ms) (callback)))))

(def my-pool (at/mk-pool))

(defn -main []
  (at/every 1000
            #(read-and-send)
            my-pool))
