
package com.app.haptikchat;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "body",
    "username",
    "Name",
    "image-url",
    "message-time"
})
public class Message {

    @JsonProperty("body")
    public String body;
    @JsonProperty("username")
    public String username;
    @JsonProperty("Name")
    public String name;
    @JsonProperty("image-url")
    public String imageUrl;
    @JsonProperty("message-time")
    public String messageTime;

    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("image-url")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("image-url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("message-time")
    public String getMessageTime() {
        return messageTime;
    }

    @JsonProperty("message-time")
    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    int userme;

    @Override
    public String toString() {
        return "Messages{" +
                "body='" + body + '\'' +
                ", username=" + username +
                '}';
    }

}
