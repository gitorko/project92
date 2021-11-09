import {Injectable} from '@angular/core';
import * as SockJS from "sockjs-client";
import {Stomp} from "@stomp/stompjs";
import {Chat} from "../models/chat";

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {
  stompClient: any;
  public messageList: Chat[] = [];

  constructor() {
  }

  _send(chat: Chat) {
    console.log("Sending chat: " + chat);
    this.stompClient.send('/app/send/message', {}, JSON.stringify(chat));
  }

  _connect() {
    const serverUrl = 'http://localhost:8080/chat-app';
    const ws = new SockJS(serverUrl);
    this.stompClient = Stomp.over(ws);
    const that = this;
    this.stompClient.connect({}, function (frame: any) {
      that.stompClient.subscribe('/message', (message: any) => {
        if (message.body) {
          that.messageList.push(JSON.parse(message.body));
        }
      });
    }, this.errorCallBack);
  };

  _disconnect() {
    if (this.stompClient !== null) {
      this.stompClient.disconnect();
    }
    console.log("Disconnected");
  }

  errorCallBack(error: any) {
    console.log("errorCallBack -> " + error)
    setTimeout(() => {
      this._connect();
    }, 5000);
  }

}
