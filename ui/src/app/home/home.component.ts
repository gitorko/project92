import {Component, OnInit} from '@angular/core';
import {Chat} from "../models/chat";
import * as SockJS from "sockjs-client";
import {Stomp} from "@stomp/stompjs";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  message: string = '';
  chatStatus: string = 'Disconnected';

  stompClient: any;
  public messageList: Chat[] = [];

  constructor() {
  }

  ngOnInit(): void {
  }

  connect() {
    console.log(window.location.href);
    const serverUrl = 'http://localhost:8080/chat-app';
    const ws = new SockJS(serverUrl);
    this.stompClient = Stomp.over(ws);
    const that = this;
    this.stompClient.connect({}, function (frame: any) {
      that.chatStatus = 'Connected';
      that.stompClient.subscribe('/message', (message: any) => {
        if (message.body) {
          that.messageList.push(JSON.parse(message.body));
        }
      });
    }, this.errorCallBack);
  }

  disconnect() {
    if (this.stompClient !== null) {
      this.stompClient.disconnect();
    }
    this.chatStatus = 'Disconnected';
    console.log("Disconnected");
  }

  sendMessage() {
    if (this.message) {
      let chat: Chat = new Chat();
      chat.text = this.message;
      console.log("Sending chat: " + chat);
      this.stompClient.send('/app/send/message', {}, JSON.stringify(chat));
      this.message = '';
    }
  }

  errorCallBack(error: any) {
    console.log("errorCallBack -> " + error)
    setTimeout(() => {
      this.connect();
    }, 5000);
  }
}
