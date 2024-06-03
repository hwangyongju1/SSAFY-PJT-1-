package com.ssafy.myssafit.model.dto.fcm;

/** 
 * @author HwangYJ
 * 
 * fcm에서 요구하는 json파일
 * spring 즉, back-end에서 firebase와 통신할 때 사용
 */
public class FcmMessageDto {
    private boolean validateOnly;
    private Message message;

    public FcmMessageDto(boolean validateOnly, Message message) {
        this.validateOnly = validateOnly;
        this.message = message;
    }

    // Getters
    public boolean isValidateOnly() {
        return validateOnly;
    }

    public Message getMessage() {
        return message;
    }

    // Builder pattern
    public static FcmMessageDtoBuilder builder() {
        return new FcmMessageDtoBuilder();
    }

    public static class FcmMessageDtoBuilder {
        private boolean validateOnly;
        private Message message;

        public FcmMessageDtoBuilder validateOnly(boolean validateOnly) {
            this.validateOnly = validateOnly;
            return this;
        }

        public FcmMessageDtoBuilder message(Message message) {
            this.message = message;
            return this;
        }

        public FcmMessageDto build() {
            return new FcmMessageDto(validateOnly, message);
        }
    }

    public static class Message {
        private Notification notification;
        private String token;

        public Message(Notification notification, String token) {
            this.notification = notification;
            this.token = token;
        }

        // Getters
        public Notification getNotification() {
            return notification;
        }

        public String getToken() {
            return token;
        }

        // Builder pattern
        public static MessageBuilder builder() {
            return new MessageBuilder();
        }

        public static class MessageBuilder {
            private Notification notification;
            private String token;

            public MessageBuilder notification(Notification notification) {
                this.notification = notification;
                return this;
            }

            public MessageBuilder token(String token) {
                this.token = token;
                return this;
            }

            public Message build() {
                return new Message(notification, token);
            }
        }
    }

    public static class Notification {
        private String title;
        private String body;
        private String image;

        public Notification(String title, String body, String image) {
            this.title = title;
            this.body = body;
            this.image = image;
        }

        // Getters
        public String getTitle() {
            return title;
        }

        public String getBody() {
            return body;
        }

        public String getImage() {
            return image;
        }

        // Builder pattern
        public static NotificationBuilder builder() {
            return new NotificationBuilder();
        }

        public static class NotificationBuilder {
            private String title;
            private String body;
            private String image;

            public NotificationBuilder title(String title) {
                this.title = title;
                return this;
            }

            public NotificationBuilder body(String body) {
                this.body = body;
                return this;
            }

            public NotificationBuilder image(String image) {
                this.image = image;
                return this;
            }

            public Notification build() {
                return new Notification(title, body, image);
            }
        }
    }

	@Override
	public String toString() {
		return "FcmMessageDto [validateOnly=" + validateOnly + ", message=" + message + "]";
	}
    
}

