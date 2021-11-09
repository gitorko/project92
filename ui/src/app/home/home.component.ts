import {Component, OnInit} from '@angular/core';
import {WebsocketService} from "../services/websocket.service";
import {Chat} from "../models/chat";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  message: string = '';
  chatStatus: string = 'Disconnected';

  constructor(public websocketService: WebsocketService) {
  }

  ngOnInit(): void {
  }

  connect() {
    this.websocketService._connect();
    this.chatStatus = 'Connected';
  }

  disconnect() {
    this.websocketService._disconnect();
    this.chatStatus = 'Disconnected';
  }

  sendMessage() {
    if (this.message) {
      let chat: Chat = new Chat();
      chat.text = this.message;
      this.websocketService._send(chat);
      this.message = '';
    }
  }

}
