package com.avr.designpatterns.creational.builder;

import java.util.ArrayList;
import java.util.List;

public class Email {
    private final String to;
    private final String subject;
    private final List<String> cc;
    private final List<String> bcc;
    private final String body;
    private final String priority;
    private final List<String> attachments;

    private Email(Builder builder) {
        to = builder.to;
        subject = builder.subject;
        cc = builder.cc;
        bcc = builder.bcc;
        body = builder.body;
        priority = builder.priority;
        attachments = builder.attachments;
    }

    public void describe() {
        System.out.println("Email {");
        System.out.println("  To          : " + to);
        System.out.println("  Subject     : " + subject);
        System.out.println("  CC          : " + cc);
        System.out.println("  BCC         : " + bcc);
        System.out.println("  Body        : " + body);
        System.out.println("  Priority    : " + priority);
        System.out.println("  Attachments : " + attachments);
        System.out.println("}");
    }

    public static class Builder {
        private final String to;
        private String subject;
        private List<String> cc;
        private List<String> bcc;
        private String body;
        private String priority;
        private List<String> attachments;

        public Builder(String to) {
            this.to = to;
            subject = new String();
            cc = new ArrayList<>();
            bcc = new ArrayList<>();
            body = new String();
            priority = new String();
            attachments = new ArrayList<>();
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder cc(String cc) {
            this.cc.add(cc);
            return this;
        }

        public Builder bcc(String bcc) {
            this.bcc.add(bcc);
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder priority(String priority) {
            this.priority = priority;
            return this;
        }

        public Builder attach(String attachment) {
            this.attachments.add(attachment);
            return this;
        }

        public Email build() {
            return new Email(this);
        }
    }

    public static void main(String[] args) {
        Email email = new Email.Builder("john@example.com")
        .subject("Builder Pattern")
        .cc("alice@example.com")
        .cc("alice2@example.com")
        .bcc("alice3@example.com")
        .body("Hello from the builder!")
        .priority("high")
        .attach("resume.pdf")
        .build();
        
        email.describe();
    }
}
