package com.avr.ClassRelationships.Association;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class SocialNetwork {
    
    @RequiredArgsConstructor
    @Getter
    @Setter
    class User {
        private final String name;
        private List<User> followers;
        private List<User> following;
        private List<Message> messages;

        public void follow(User user) {
            if(this.following == null)
                this.following = new ArrayList<>();
            this.following.add(user);
            user.addFollower(this);
        }
        private void addFollower(User user) {
            if(this.followers == null)
                this.followers = new ArrayList<>();
            this.followers.add(user);
        }

        public void sendMessage(String content, LocalDateTime timeStamp) {
            if(this.messages == null) 
                this.messages = new ArrayList<>();
            var message = new Message(this, content, timeStamp);
            this.messages.add(message);
        }
    }

    @RequiredArgsConstructor
    @Getter
    @Setter
    class Message {
        private final User author;
        private final String content;
        private final LocalDateTime timeStamp;
    }
    public static void main(String[] args) {
        SocialNetwork socialNetwork = new SocialNetwork();
        User alice = socialNetwork.new User("Alice");
        User bob = socialNetwork.new User("Bob");
        User charlie = socialNetwork.new User("Charlie");

        alice.follow(bob);
        alice.follow(charlie);
        bob.follow(alice);

        alice.sendMessage("Hello world!", LocalDateTime.now());
        bob.sendMessage("Learning OOP!", LocalDateTime.now());

        System.out.println(alice.getName() + " is following:");
        for (User u : alice.getFollowing())
            System.out.println("  - " + u.getName());

        System.out.println(bob.getName() + "'s followers:");
        for (User u : bob.getFollowers())
            System.out.println("  - " + u.getName());

        System.out.println(alice.getName() + "'s messages:");
        for (Message m : alice.getMessages())
            System.out.println("  [" + m.getTimeStamp().toString() + "] " + m.getContent());
    }
}
