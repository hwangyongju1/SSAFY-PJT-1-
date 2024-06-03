package com.ssafy.myssafit.model.fcm;

public enum fcmMessage {

//	MORNING_TITLE("아침 먹음?"),
//	MORINIG_BODY("안먹었으면 먹어")
	
	DEVELOPER_TWO("새벽 4시 45분", "개발자님 스트레칭으로 허리를 펴봐요"),
	DEVELOPER_FOUR("오후 4시에요.", "잠시 바람쐐고 오세요"),
	STUDENT_TWO("오후 2시 쉬는 시간이에요.", "잠시 산책이라도 다녀오세요"),
	STUDENT_FOUR("오후 4시 쉬는 시간이에요.", "스트레칭할 시간이에요");
	
//    MORNING_DIET("아침 식사 드셨나요?", "아침 식사를 드셨다면, 식사를 기록해주세요!"),
//    LUNCH_DIET("점심 식사 드셨나요?", "점심 식사를 드셨다면, 식사를 기록해주세요!"),
//    DINNER_DIET("저녁 식사 드셨나요?", "저녁 식사를 드셨다면, 식사를 기록해주세요!");

    private final String title;
    private final String body;

    // 생성자
    fcmMessage(String title, String body) {
        this.title = title;
        this.body = body;
    }

    // Getter 메서드
    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
