<template>
    <h2 class="container"><strong>GPT에게 궁금한 것을 물어보세요</strong></h2>
  <div class="chat-container">
    <!-- gpt 입력 창 -->
    <input type="text" v-model="question" @keypress.enter="gptChat" placeholder="질문해주세요" class="chat-input">
    <button @click="gptChat" class="chat-button">Send</button>

    <!-- 대화 내용 표시 -->
    <div class="chat-answer-box">
      <p v-if="answer"><strong>GPT:</strong> {{ answer }}</p>
      <p v-else>질문을 해주세요</p>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';

const gptKey = import.meta.env.VITE_GPT_API_KEY;

const question = ref("");
const answer = ref("");

const gptChat = async () => {
  if (!question.value) return;

  try {
    const response = await axios.post('https://api.openai.com/v1/chat/completions', {
      model: 'gpt-3.5-turbo',
      messages: [{ role: 'user', content: question.value }]
    }, {
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${gptKey}`
      }
    });

    answer.value = response.data.choices[0].message.content;
  } catch (error) {
    console.error(error);
    answer.value = "An error occurred while communicating with GPT.";
  }
}
</script>

<style scoped>
.chat-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 20vh;
}

.chat-input {
  width: 50%;
  padding: 10px;
  margin: 10px 0;
  box-sizing: border-box;
  border-radius: 8px;
}

.chat-button {
  padding: 10px 20px;
  margin: 10px 0;
  cursor: pointer;
  border-radius: 8px;
}

.chat-answer-box {
  width: 50%;
  min-height: 40px;
  padding: 10px;
  margin-top: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  text-align: left;
  word-wrap: break-word;
  transition: min-height 0.2s ease-in-out;
}

.chat-answer-box p {
  margin: 0;
}
</style>
