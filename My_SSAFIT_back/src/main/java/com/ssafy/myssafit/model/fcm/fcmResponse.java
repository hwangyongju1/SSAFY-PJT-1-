package com.ssafy.myssafit.model.fcm;

public class fcmResponse {
    private int status;
    private String message;
    
    
    public fcmResponse() {
    }
    
    public fcmResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

	@Override
	public String toString() {
		return "PushNotificationResponse [status=" + status + ", message=" + message + "]";
	}
    
}