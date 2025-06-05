package com.example.bubbletalk20

import android.app.Dialog
import android.os.Handler
import android.graphics.Color
import android.os.Looper
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class LetterWordsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LetterWordsAdapter
    private lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_letter_words)

        tts = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                // TTS is ready to use
                val result = tts.setLanguage(Locale.getDefault())
                // Now you can safely use tts
            }
        }

        // 返回按鈕
        val btnBack = findViewById<Button>(R.id.btn_back)
        btnBack.setOnClickListener {
            showImageThenFinish()//上一頁
        }

        // 取得從 A~Z 點選的字母
        val letter = intent.getStringExtra("letter") ?: "A"
        val words = getWordsForLetter(letter)

        // RecyclerView 設定
        recyclerView = findViewById(R.id.recyclerViewWords)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = LetterWordsAdapter(words, tts)
        recyclerView.adapter = adapter
    }
    private fun showImageThenFinish() {
        // 建立一個不會自動關閉的對話框 (Dialog)
        val dialog = Dialog(this)
        // 使用剛才建立的 layout
        dialog.setContentView(R.layout.dialog_image)
        // 按對話框之外不要自動關閉
        dialog.setCancelable(false)
        dialog.show()

        // Handler 延遲 2 秒後關掉對話框並 finish()
        Handler(Looper.getMainLooper()).postDelayed({
            dialog.dismiss()
            finish()
        }, 2000)
    }


    // 回傳對應 15 個單字
    private fun getWordsForLetter(letter: String): List<String> {
        return when (letter.uppercase()) {

            "A" -> listOf("above(prep.)在…上面 ㄗㄞˋ ㄕㄤˋ ㄇㄧㄢˋ", "afraid(adj.)害怕的 ㄏㄞˋ ㄆㄚˋ ㄉㄜ˙", "afternoon(n.)下午 ㄒㄧㄚˋ ㄨˇ", "again(adv.)再一次 ㄗㄞˋ ㄧ ㄘˋ", "angry(adj.)生氣的 ㄕㄥ ㄑㄧˋ ㄉㄜ˙", "anyone(n.)任何人 ㄖㄣˊ ㄏㄜˊ ㄖㄣˊ", "apple(n.)蘋果 ㄆㄧㄥˊ ㄍㄨㄛˇ", "arrive(v.)到達 ㄉㄠˋ ㄉㄚˊ", "answer(v.)回答 ㄏㄨㄟˊ ㄉㄚˊ", "airplane(n.)飛機 ㄈㄟ ㄐㄧ", "animal(n.)動物 ㄉㄨㄥˋ ㄨˋ", "autumn(n.)秋天 ㄑㄧㄡ ㄊㄧㄢ", "also(adv.)也 ㄧㄝˇ", "almost(adv.)幾乎 ㄐㄧ ㄏㄨ", "air(n.)空氣 ㄎㄨㄥ ㄑㄧˋ")
            "B" -> listOf("baby(n.)寶寶 ㄅㄠˇ ㄅㄠˇ", "bad(adj.)壞的 ㄏㄨㄞˋ ㄉㄜ˙", "bag(n.)包包 ㄅㄠ ㄅㄠ", "ball(n.)球 ㄑㄧㄡˊ", "banana(n.)香蕉 ㄒㄧㄤ ㄐㄧㄠ", "band(n.)樂團 ㄩㄝˋ ㄊㄨㄢˊ", "bear(n.)熊 ㄒㄩㄥˊ", "beautiful(adj.)美麗的 ㄇㄟˇ ㄌㄧˋ ㄉㄜ˙", "bedroom(n.)房間 ㄈㄤˊ ㄐㄧㄢ", "beef(n.)牛肉 ㄋㄧㄡˊ ㄖㄡˋ", "believe(v.)相信 ㄒㄧㄤ ㄒㄧㄣˋ", "blue(n.)藍色 ㄌㄢˊ ㄙㄜˋ", "body(n.)身體 ㄕㄣ ㄊㄧˇ", "borrow(v.)借 ㄐㄧㄝˋ", "boy(n.)男孩 ㄋㄢˊ ㄏㄞˊ")
            "C" -> listOf("cake(n.)蛋糕 ㄉㄢˋ ㄍㄠ", "camera(n.)相機 ㄒㄧㄤˋ ㄐㄧ", "candy(n.)糖果 ㄊㄤˊ ㄍㄨㄛˇ", "card(n.)卡片 ㄎㄚˇ ㄆㄧㄢˋ", "cat(n.)貓 ㄇㄠ", "celebrate(v.)慶祝 ㄑㄧㄥˋ ㄓㄨˋ", "chair(n.)椅子 ㄧˇ ㄗ˙", "check(v.)檢查 ㄐㄧㄢˇ ㄔㄚˊ", "chicken(n.)烤雞 ㄎㄠˇ ㄐㄧ", "chocolate(n.)巧克力 ㄑㄧㄠˇ ㄎㄜˋ ㄌㄧˋ", "clean(v.)清潔 ㄑㄧㄥ ㄐㄧㄝˊ", "close(v.)關 ㄍㄨㄢ", "come(v.)來 ㄌㄞˊ", "cook(v.)烹煮 ㄆㄥ ㄓㄨˇ", "correct(adj.)正確的 ㄓㄥˋ ㄑㄩㄝˋ ㄉㄜ˙")
            "D" -> listOf("dance(v.)跳舞 ㄊㄧㄠˋ ㄨˇ", "date(n.)日期 ㄖˋ ㄑㄧˊ", "day(n.)天 ㄊㄧㄢ", "desk(n.)桌子 ㄓㄨㄛ ㄗ˙", "dinner(n.)晚餐 ㄨㄢˇ ㄘㄢ", "doctor(n.)醫生 ㄧ ㄕㄥ", "doll(n.)娃娃 ㄨㄚˊ ㄨㄚˊ", "door(n.)門 ㄇㄣˊ", "dress(n.)服裝 ㄈㄨˊ ㄓㄨㄤ", "drink(v.)喝 ㄏㄜ", "drive(v.)開車 ㄎㄞ ㄔㄜ", "dog(n.)狗狗 ㄍㄡˇ ㄍㄡˇ", "dollar(n.)元 ㄩㄢˊ", "down(prep.)往下 ㄨㄤˇ ㄒㄧㄚˋ", "dream(n.)夢想 ㄇㄥˋ ㄒㄧㄤˇ")
            "E" -> listOf("each(prep.)每個 ㄇㄟˇ ㄍㄜˋ", "earth(n.)地球 ㄉㄧˋ ㄑㄧㄡˊ", "eat(v.)吃 ㄔ", "egg(n.)雞蛋 ㄐㄧ ㄉㄢˋ", "eleven(n.)十一 ㄕˊ ㄧ", "end(n.)結束 ㄐㄧㄝˊ ㄕㄨˋ", "enough(adj.)足夠的 ㄗㄨˊ ㄍㄡˋ ㄉㄜ˙", "enter(v.)進入 ㄐㄧㄣˋ ㄖㄨˋ", "even(adv.)即使 ㄐㄧˊ ㄕˇ", "everyone(n.)每個人 ㄇㄟˇ ㄍㄜˋ ㄖㄣˊ", "example(n.)舉例 ㄐㄩˇ ㄌㄧˋ", "excellent(adj.)優秀的 ㄧㄡ ㄒㄧㄡˋ ㄉㄜ˙", "excuse(n.)藉口 ㄐㄧㄝˋ ㄎㄡˇ", "expensive(adj.)昂貴的 ㄤˊ ㄍㄨㄟˋ ㄉㄜ˙", "eye(n.)眼睛 ㄧㄢˇ ㄐㄧㄥ")
            "F" -> listOf("face(n.)臉 ㄌㄧㄢˇ", "fall(v.)落下 ㄌㄨㄛˋ ㄒㄧㄚˋ", "family(n.)家庭 ㄐㄧㄚ ㄊㄧㄥˊ", "famous(adj.)有名的 ㄧㄡˇ ㄇㄧㄥˊ ㄉㄜ˙", "farmer(n.)農夫 ㄋㄨㄥˊ ㄈㄨ", "feel(v.)感受 ㄍㄢˇ ㄕㄡˋ", "find(v.)找尋 ㄓㄠˇ ㄒㄩㄣˊ", "finish(v.)完成 ㄨㄢˊ ㄔㄥˊ", "fire(n.)火 ㄏㄨㄛˇ", "first(adj.)第一個 ㄉㄧˋ ㄧ ㄍㄜˋ", "friend(n.)朋友 ㄆㄥˊ ㄧㄡˇ", "fun(v.)開玩笑 ㄎㄞ ㄨㄢˊ ㄒㄧㄠˋ", "funny(adj.)有趣的 ㄧㄡˇ ㄑㄩˋ ㄉㄜ˙", "future(n.)未來 ㄨㄟˋ ㄌㄞˊ", "fruit(n.)水果 ㄕㄨㄟˇ ㄍㄨㄛˇ")
            "G" -> listOf("game(n.)遊戲 ㄧㄡˊ ㄒㄧˋ", "garden(n.)花園 ㄏㄨㄚ ㄩㄢˊ", "gift(n.)禮物 ㄌㄧˇ ㄨˋ", "girl(n.)女孩 ㄋㄩˇ ㄏㄞˊ", "glass(n.)眼鏡 ㄧㄢˇ ㄐㄧㄥˋ", "goat(n.)山羊 ㄕㄢ ㄧㄤˊ", "good(adj.)良好的 ㄌㄧㄤˊ ㄏㄠˇ ㄉㄜ˙", "grade(n.)成績 ㄔㄥˊ ㄐㄧ", "grass(n.)草 ㄘㄠˇ", "green(n.)綠色 ㄌㄩˋ ㄙㄜˋ", "great(adj.)重大的 ㄓㄨㄥˋ ㄉㄚˋ ㄉㄜ˙", "group(n.)群 ㄑㄩㄣˊ", "guess(v.)猜測 ㄘㄞ ㄘㄜˋ", "give(v.)給予 ㄍㄟˇ ㄩˇ", "go(v.)去 ㄑㄩˋ")
            "H" -> listOf("habit(n.)習慣 ㄒㄧˊ ㄍㄨㄢˋ", "hand(n.)手掌 ㄕㄡˇ ㄓㄤˇ", "handsome(adj.)帥氣的 ㄕㄨㄞˋ ㄑㄧˋ ㄉㄜ˙", "happy(adj.)開心的 ㄎㄞ ㄒㄧㄣ ㄉㄜ˙", "hard(adj.)困難的 ㄎㄨㄣˋ ㄋㄢˊ ㄉㄜ˙", "hat(n.)帽子 ㄇㄠˋ ㄗ˙", "hate(v.)討厭 ㄊㄠˇ ㄧㄢˋ", "head(n.)頭 ㄊㄡˊ", "heart(n.)心臟 ㄒㄧㄣ ㄗㄤˋ", "heavy(adj.)重的 ㄓㄨㄥˋ ㄉㄜ˙", "hello(n.)你好 ㄋㄧˇ ㄏㄠˇ", "home(n.)家 ㄐㄧㄚ", "hope(n.)希望 ㄒㄧ ㄨㄤˋ", "hour(n.)小時 ㄒㄧㄠˇ ㄕˊ", "hungry(adj.)飢餓的 ㄐㄧ ㄜˋ ㄉㄜ˙")
            "I" -> listOf("ice(n.)冰 ㄅㄧㄥ", "idea(n.)點子 ㄉㄧㄢˇ ㄗ˙", "important(adj.)重要的 ㄓㄨㄥˋ ㄧㄠˋ ㄉㄜ˙", "inside(adv.)在…裡面 ㄗㄞˋ ㄌㄧˇ ㄇㄧㄢˋ", "interest(n.)嗜好 ㄕˋ ㄏㄠˋ", "internet(n.)網路 ㄨㄤˇ ㄌㄨˋ", "island(n.)島嶼 ㄉㄠˇ ㄩˇ", "include(v.)包含 ㄅㄠ ㄏㄢˊ", "imagine(n.)圖片 ㄊㄨˊ ㄆㄧㄢˋ", "ignore(v.)忽略 ㄏㄨ ㄌㄩㄝˋ", "item(n.)物件 ㄨˋ ㄐㄧㄢˋ", "interview(n.)面試 ㄇㄧㄢˋ ㄕˋ", "introduce(v.)介紹 ㄐㄧㄝˋ ㄕㄠˋ", "influence(n.)影響 ㄧㄥˇ ㄒㄧㄤˇ", "ink(n.)墨水 ㄇㄛˋ ㄕㄨㄟˇ")
            "J" -> listOf("jeans(n.)牛仔褲 ㄋㄧㄡˊ ㄗㄞˇ ㄎㄨˋ", "job(n.)工作 ㄍㄨㄥ ㄗㄨㄛˋ", "join(v.)參加 ㄘㄢ ㄐㄧㄚ", "juice(n.)果汁 ㄍㄨㄛˇ ㄓ", "jump(v.)跳 ㄊㄧㄠˋ", "just(adv.)剛剛 ㄍㄤ ㄍㄤ", "jazz(n.)爵士樂 ㄐㄩㄝˊ ㄕˋ ㄩㄝˋ", "joint(v.)連接 ㄌㄧㄢˊ ㄐㄧㄝ", "juicy(adj.)多汁的 ㄉㄨㄛ ㄓ ㄉㄜ˙", "jam(n.)果醬 ㄍㄨㄛˇ ㄐㄧㄤˋ", "jacket(n.)夾克 ㄐㄧㄚˊ ㄎㄜˋ", "jealously(adv.)忌妒地 ㄐㄧˋ ㄉㄨˋ ㄉㄧˋ", "June(n.)六月 ㄌㄧㄡˋ ㄩㄝˋ", "July(n.)七月 ㄑㄧ ㄩㄝˋ", "Japan(n.)日本 ㄖˋ ㄅㄣˇ")
            "K" -> listOf("key(n.)鑰匙 ㄧㄠˋ ㄕ˙", "kid(n.)小孩 ㄒㄧㄠˇ ㄏㄞˊ", "kind(adj.)親切的 ㄑㄧㄣ ㄑㄧㄝˋ ㄉㄜ˙", "king(n.)國王 ㄍㄨㄛˊ ㄨㄤˊ", "kiss(v.)親吻 ㄑㄧㄣ ㄨㄣˇ", "knife(n.)刀 ㄉㄠ", "knock(v.)敲 ㄑㄧㄠ", "know(v.)理解 ㄌㄧˇ ㄐㄧㄝˇ", "knowledge(n.)知識 ㄓ ㄕ", "kick(v.)踢 ㄊㄧ", "keep(v.)保持 ㄅㄠˇ ㄔˊ", "kitchen(n.)廚房 ㄔㄨˊ ㄈㄤˊ", "koala(n.)無尾熊 ㄨˊ ㄨㄟˇ ㄒㄩㄥˊ", "ketchup(n.)番茄醬 ㄈㄢ ㄑㄧㄝˊ ㄐㄧㄤˋ", "kraft(n.)牛皮紙 ㄋㄧㄡˊ ㄆㄧˊ ㄓˇ")
            "L" -> listOf("lake(n.)湖 ㄏㄨˊ", "land(n.)陸地 ㄌㄨˋ ㄉㄧˋ", "large(adj.)巨大的 ㄐㄩˋ ㄉㄚˋ ㄉㄜ˙", "late(adv.)遲到 ㄔˊ ㄉㄠˋ", "laugh(n.)笑容 ㄒㄧㄠˋ ㄖㄨㄥˊ", "lazy(adj.)慵懶的 ㄩㄥ ㄌㄢˇ ㄉㄜ˙", "leave(v.)離開 ㄌㄧˊ ㄎㄞ", "lemon(n.)檸檬 ㄋㄧㄥˊ ㄇㄥˊ", "less(prep.)較小 ㄐㄧㄠˋ ㄒㄧㄠˇ", "letter(n.)信封 ㄒㄧㄣˋ ㄈㄥ", "library(n.)圖書館 ㄊㄨˊ ㄕㄨ ㄍㄨㄢˇ", "life(n.)人生 ㄖㄣˊ ㄕㄥ", "lion(n.)獅子 ㄕ ㄗ˙", "love(n.)愛情 ㄞˋ ㄑㄧㄥˊ", "lucky(adj.)幸運的 ㄒㄧㄥˋ ㄩㄣˋ ㄉㄜ˙")
            "M" -> listOf("magic(n.)魔術 ㄇㄛˊ ㄕㄨˋ", "make(v.)做 ㄗㄨㄛˋ", "many(adj.)非常多的 ㄈㄟ ㄔㄤˊ ㄉㄨㄛ ㄉㄜ˙", "mark(n.)標記 ㄅㄧㄠ ㄐㄧˋ", "matter(v.)有問題 ㄧㄡˇ ㄨㄣˋ ㄊㄧˊ", "maybe(adv.)也許 ㄧㄝˇ ㄒㄩˇ", "meat(n.)肉類 ㄖㄡˋ ㄌㄟˋ", "menu(n.)菜單 ㄘㄞˋ ㄉㄢ", "milk(n.)牛奶 ㄋㄧㄡˊ ㄋㄞˇ", "minute(n.)分鐘 ㄈㄣ ㄓㄨㄥ", "moon(n.)月亮 ㄩㄝˋ ㄌㄧㄤˋ", "mother(n.)媽媽 ㄇㄚ ㄇㄚ", "movie(n.)電影 ㄉㄧㄢˋ ㄧㄥˇ", "mouse(n.)老鼠 ㄌㄠˇ ㄕㄨˇ", "museum(n.)博物館 ㄅㄛˊ ㄨˋ ㄍㄨㄢˇ")
            "N" -> listOf("name(n.)名字，姓名 ㄇㄧㄥˊ ㄗˋ ㄒㄧㄥˋ ㄇㄧㄥˊ", "national(adj.)全國性的 ㄑㄩㄢˊ ㄍㄨㄛˊ ㄒㄧㄥˋ ㄉㄜ˙", "near(adj.)近的 ㄐㄧㄣˋ ㄉㄜ˙", "neck(n.)脖子 ㄅㄛˊ ㄗ˙", "need(v./n.)需要 ㄒㄩ ㄧㄠˋ", "never(adv.)從未，永不 ㄘㄨㄥˊ ㄨㄟˋ ㄩㄥˇ ㄅㄨˋ", "new(adj.)新的 ㄒㄧㄣ ㄉㄜ˙", "news(n.)新聞，消息 ㄒㄧㄣ ㄨㄣˊ ㄒㄧㄠ ㄒㄧ", "next(adj.)下一個的 ㄒㄧㄚˋ ㄧ ㄍㄜˋ ㄉㄜ˙", "nice(adj.)美好的 ㄇㄟˇ ㄏㄠˇ ㄉㄜ˙", "night(n.)夜晚 ㄧㄝˋ ㄨㄢˇ", "nine(adj./n.)九 ㄐㄧㄡˇ", "nineteen(adj./n.)十九 ㄕˊ ㄐㄧㄡˇ", "ninety(adj./n.)九十 ㄐㄧㄡˇ ㄕˊ", "ninth(adj./n.)第九 ㄉㄧˋ ㄐㄧㄡˇ")
            "O" -> listOf("o'clock(adv.)…點鐘 ㄉㄧㄢˇ ㄓㄨㄥ", "October(n.)十月 ㄕˊ ㄩㄝˋ", "of(prep.)…的，屬於 ㄉㄜ˙ ㄕㄨˇ ㄩˊ", "off(adv./prep.)(離)開，(走)開 ㄌㄧˊ ㄎㄞ ㄗㄡˇ ㄎㄞ", "office(n.)辦公室 ㄅㄢˋ ㄍㄨㄥ ㄕˋ", "officer(n.)軍官，官員 ㄐㄩㄣ ㄍㄨㄢ ㄍㄨㄢ ㄩㄢˊ", "often(adv.)常常，時常 ㄔㄤˊ ㄔㄤˊ ㄕˊ ㄔㄤˊ", "oil(n.)油 ㄧㄡˊ", "O.K.(adj.)可以的，不錯的 ㄎㄜˇ ㄧˇ ㄉㄜ˙ ㄅㄨˋ ㄘㄨㄛˋ ㄉㄜ˙", "old(adj.)老的，年老的 ㄌㄠˇ ㄉㄜ˙ ㄋㄧㄢˊ ㄌㄠˇ ㄉㄜ˙", "on(prep.)在…上 ㄗㄞˋ ㄕㄤˋ", "once(adv.)一次，一回 ㄧ ㄘˋ ㄧ ㄏㄨㄟˊ", "one(n./adj.)一；一個 ㄧ ㄧ ㄍㄜˋ", "only(adj./adv.)唯一的，僅有的 ㄨㄟˊ ㄧ ㄉㄜ˙ ㄐㄧㄣˇ ㄧㄡˇ ㄉㄜ˙", "open(adj./v.)打開的；打開 ㄉㄚˇ ㄎㄞ ㄉㄜ˙ ㄉㄚˇ ㄎㄞ")
            "P" -> listOf("pack(n./v.)包，捆，包裹；打包 ㄅㄠ ㄎㄨㄣˇ ㄅㄠ ㄍㄨㄛˇ ㄉㄚˇ ㄅㄠ", "package(n.)包裹 ㄅㄠ ㄍㄨㄛˇ", "page(n.)頁，頁面 ㄧㄝˋ ㄧㄝˋ ㄇㄧㄢˋ", "paint(n./v.)油漆；塗料；上漆 ㄧㄡˊ ㄑㄧ ㄊㄨˊ ㄌㄧㄠˋ ㄕㄤˋ ㄑㄧ", "pair(n.)一對，一雙 ㄧ ㄉㄨㄟˋ ㄧ ㄕㄨㄤ", "pants(n.)褲子 ㄎㄨˋ ㄗ˙", "paper(n.)紙；報紙；文件 ㄓˇ ㄅㄠˋ ㄓˇ ㄨㄣˊ ㄐㄧㄢˋ", "parent(n.)父親或母親 ㄈㄨˋ ㄑㄧㄣ ㄏㄨㄛˋ ㄇㄨˇ ㄑㄧㄣ", "park(n./v.)公園；停車 ㄍㄨㄥ ㄩㄢˊ ㄊㄧㄥˊ ㄔㄜ", "part(n.)部分 ㄅㄨˋ ㄈㄣˋ", "party(n.)聚會；政黨 ㄐㄩˋ ㄏㄨㄟˋ")
            "Q" -> listOf("queen(n.)女王 ㄋㄩˇ ㄨㄤˊ", "question(n.)問題 ㄨㄣˋ ㄊㄧˊ；詢問 ㄒㄩㄣˊ ㄨㄣˋ", "quick(adj.)快的 ㄎㄨㄞˋ ㄉㄜ˙，迅速的 ㄒㄩㄣˋ ㄙㄨˋ ㄉㄜ˙", "quiet(adj.)安靜的 ㄢ ㄐㄧㄥˋ ㄉㄜ˙", "quite(adv.)完全地 ㄨㄢˊ ㄑㄩㄢˊ ㄉㄧˋ，確實地 ㄑㄩㄝˋ ㄕˊ ㄉㄧˋ", "quarter(n.)四分之一 ㄙˋ ㄈㄣ ㄓ ㄧ；一刻鐘 ㄧˋ ㄎㄜˋ ㄓㄨㄥ", "quiz(n.)小考 ㄒㄧㄠˇ ㄎㄠˇ；測驗 ㄘㄜˋ ㄧㄢˋ", "queenly(adj.)有女王風範的 ㄧㄡˇ ㄋㄩˇ ㄨㄤˊ ㄈㄥ ㄈㄢˋ ㄉㄜ˙", "quickly(adv.)快速地 ㄎㄨㄞˋ ㄙㄨˋ ㄉㄧˋ", "quietness(n.)寂靜 ㄐㄧˋ ㄐㄧㄥˋ；安靜 ㄢ ㄐㄧㄥˋ", "qualify(v.)有資格 ㄧㄡˇ ㄗ ㄍㄜˊ；使合格 ㄕˇ ㄏㄜˊ ㄍㄜˊ", "questioner(n.)發問者 ㄈㄚ ㄨㄣˋ ㄓㄜˇ；提問者 ㄊㄧˊ ㄨㄣˋ ㄓㄜˇ", "quotation(n.)引文 ㄧㄣˇ ㄨㄣˊ；報價 ㄅㄠˋ ㄐㄧㄚˋ", "quarrel(n./v.)爭吵 ㄓㄥ ㄔㄠˇ", "quote(v./n.)引用 ㄧㄣˇ ㄩㄥˋ；報價 ㄅㄠˋ ㄐㄧㄚˋ")
            "R" -> listOf("rabbit(n.)兔子 ㄊㄨˋ ㄗˇ", "radio(n.)收音機 ㄕㄡ ㄧㄣ ㄐㄧ；無線電 ㄨˊ ㄒㄧㄢˋ ㄉㄧㄢˋ", "railway(n.)鐵路 ㄊㄧㄝˇ ㄌㄨˋ", "rain(n./v.)雨 ㄩˇ", "rainbow(n.)彩虹 ㄘㄞˇ ㄏㄨㄥˊ", "rainy(adj.)下雨的 ㄒㄧㄚˋ ㄩˇ ㄉㄜ˙，多雨的 ㄉㄨㄛ ㄩˇ ㄉㄜ˙", "read(v.)閱讀 ㄩㄝˋ ㄉㄨˊ", "ready(adj.)準備好的 ㄓㄨㄣˇ ㄅㄟˋ ㄏㄠˇ ㄉㄜ˙", "real(adj.)真實的 ㄓㄣ ㄕˊ ㄉㄜ˙", "really(adv.)真地 ㄓㄣ ㄉㄧˋ，確實地 ㄑㄩㄝˋ ㄕˊ ㄉㄧˋ", "red(adj./n.)紅色的 ㄏㄨㄥˊ ㄙㄜˋ ㄉㄜ˙", "refrigerator(n.)冰箱 ㄅㄧㄥ ㄒㄧㄤ", "remember(v.)記得 ㄐㄧˋ ㄉㄜˊ", "repeat(v.)重複 ㄓㄨㄥˋ ㄈㄨˋ", "rest(n./v.)休息 ㄒㄧㄡ ㄒㄧ")
            "S" -> listOf("sad(adj.)傷心的 ㄕㄤ ㄒㄧㄣ ㄉㄜ˙，悲哀的 ㄅㄟ ㄞ ㄉㄜ˙", "safe(adj.)安全的 ㄢ ㄑㄩㄢˊ ㄉㄜ˙", "salad(n.)沙拉 ㄕㄚ ㄌㄚ", "sale(n.)出售 ㄔㄨ ㄕㄡˋ，賣出 ㄇㄞˋ ㄔㄨ", "salt(n.)鹽 ㄧㄢˊ", "same(adj.)相同的 ㄒㄧㄤ ㄊㄨㄥˊ ㄉㄜ˙，同樣的 ㄊㄨㄥˊ ㄧㄤˋ ㄉㄜ˙", "sandwich(n.)三明治 ㄙㄢ ㄇㄧㄥˊ ㄓˋ", "Saturday(n.)星期六 ㄒㄧㄥ ㄑㄧˊ ㄌㄧㄡˋ", "save(v.)拯救 ㄓㄥˇ ㄐㄧㄡˋ；儲存 ㄔㄨˇ ㄘㄨㄣˊ；節省 ㄐㄧㄝˊ ㄕㄥˇ", "say(v.)說 ㄕㄨㄛ", "school(n.)學校 ㄒㄩㄝˊ ㄒㄧㄠˋ", "sea(n.)海 ㄏㄞˇ", "season(n.)季節 ㄐㄧˋ ㄐㄧㄝˊ", "seat(n.)座位 ㄗㄨㄛˋ ㄨㄟˋ", "second(adj./n.)第二的 ㄉㄧˋ ㄦˋ ㄉㄜ˙；秒鐘 ㄇㄧㄠˇ ㄓㄨㄥ")
            "T" -> listOf("table(n.)桌子 ㄓㄨㄛ ㄗˇ", "Taiwan(n.)台灣 ㄊㄞˊ ㄨㄢ", "take(v.)拿 ㄋㄚˊ，取 ㄑㄩˇ", "talk(v.)講話 ㄐㄧㄤˇ ㄏㄨㄚˋ，談話 ㄊㄢˊ ㄏㄨㄚˋ", "tall(adj.)高的 ㄍㄠ ㄉㄜ˙（身高 ㄕㄣ ㄍㄠ）", "tape(n.)膠帶 ㄐㄧㄠ ㄉㄞˋ；錄音帶 ㄌㄨˋ ㄧㄣ ㄉㄞˋ", "taste(n./v.)味道 ㄨㄟˋ ㄉㄠˋ；品嚐 ㄆㄧㄣˇ ㄔㄤˊ", "taxi(n.)計程車 ㄐㄧˋ ㄔㄥˊ ㄔㄜ", "tea(n.)茶 ㄔㄚˊ", "teach(v.)教 ㄐㄧㄠ", "teacher(n.)老師 ㄌㄠˇ ㄕ", "team(n.)隊伍 ㄉㄨㄟˋ ㄨˇ", "teenager(n.)青少年 ㄑㄧㄥ ㄕㄠˋ ㄋㄧㄢˊ", "telephone(n.)電話 ㄉㄧㄢˋ ㄏㄨㄚˋ", "television(n.)電視 ㄉㄧㄢˋ ㄕˋ")
            "U" -> listOf("umbrella(n.)雨傘 ㄩˇ ㄙㄢˇ", "uncle(n.)叔叔 ㄕㄨ ㄕㄨ；伯伯 ㄅㄛˊ ㄅㄛˊ；舅舅等 ㄐㄧㄡˋ ㄐㄧㄡˋ ㄉㄥˇ", "under(prep.)在…下面 ㄗㄞˋ...ㄒㄧㄚˋ ㄇㄧㄢˋ", "understand(v.)理解 ㄌㄧˇ ㄐㄧㄝˇ", "unhappy(adj.)不快樂的 ㄅㄨˋ ㄎㄨㄞˋ ㄌㄜˋ ㄉㄜ˙，不幸福的 ㄅㄨˋ ㄒㄧㄥˋ ㄈㄨˊ ㄉㄜ˙", "uniform(n./adj.)制服 ㄓˋ ㄈㄨˊ；一致的 ㄧˋ ㄓˋ ㄉㄜ˙", "until(prep./conj.)直到…為止 ㄓˊ ㄉㄠˋ...ㄨㄟˊ ㄓˇ", "up(adv./prep.)向上 ㄒㄧㄤˋ ㄕㄤˋ", "USA(n.)美國 ㄇㄟˇ ㄍㄨㄛˊ (=United States of America)", "use(v./n.)使用 ㄕˇ ㄩㄥˋ", "useful(adj.)有用的 ㄧㄡˇ ㄩㄥˋ ㄉㄜ˙", "usually(adv.)通常地 ㄊㄨㄥ ㄔㄤˊ ㄉㄧˋ", "ugly(adj.)醜陋的 ㄔㄡˇ ㄌㄡˋ ㄉㄜ˙", "upload(v.)上傳 ㄕㄤˋ ㄔㄨㄢˊ", "unfair(adj.)不公平的 ㄅㄨˋ ㄍㄨㄥ ㄆㄧㄥˊ ㄉㄜ˙")
            "V" -> listOf("vacation(n.)假期 ㄐㄧㄚˋ ㄑㄧˊ", "vegetable(n.)蔬菜 ㄕㄨ ㄘㄞˋ", "very(adv.)非常 ㄈㄟ ㄔㄤˊ，很 ㄏㄣˇ", "video(n.)錄影 ㄌㄨˋ ㄧㄥˇ；影片 ㄧㄥˇ ㄆㄧㄢˋ", "visit(v./n.)拜訪 ㄅㄞˋ ㄈㄤˇ；參觀 ㄘㄢ ㄍㄨㄢ", "voice(n.)聲音 ㄕㄥ ㄧㄣ", "village(n.)村莊 ㄘㄨㄣ ㄓㄨㄤ", "violin(n.)小提琴 ㄒㄧㄠˇ ㄊㄧˊ ㄑㄧㄣˊ", "valuable(adj.)有價值的 ㄧㄡˇ ㄐㄧㄚˋ ㄓˊ ㄉㄜ˙", "variety(n.)多樣性 ㄉㄨㄛ ㄧㄤˋ ㄒㄧㄥˋ", "verb(n.)動詞 ㄉㄨㄥˋ ㄘˊ", "view(n.)視野 ㄕˋ ㄧㄝˇ；風景 ㄈㄥ ㄐㄧㄥˇ", "victory(n.)勝利 ㄕㄥˋ ㄌㄧˋ", "vote(v./n.)投票 ㄊㄡˊ ㄆㄧㄠˋ；表決 ㄅㄧㄠˇ ㄐㄩㄝˊ", "voice mail(n.)語音信箱 ㄩˇ ㄧㄣ ㄒㄧㄣˋ ㄒㄧㄤ")
            "W" -> listOf("wait(v.)等待 ㄉㄥˇ ㄉㄞˋ", "waiter(n.)男服務生 ㄋㄢˊ ㄈㄨˊ ㄨˋ ㄕㄥ", "waitress(n.)女服務生 ㄋㄩˇ ㄈㄨˊ ㄨˋ ㄕㄥ", "wake(v.)醒來 ㄒㄧㄥˇ ㄌㄞˊ", "walk(v./n.)走路 ㄗㄡˇ ㄌㄨˋ；散步 ㄙㄢˋ ㄅㄨˋ", "wall(n.)牆 ㄑㄧㄤˊ", "want(v.)想要 ㄒㄧㄤˇ ㄧㄠˋ", "warm(adj.)溫暖的 ㄨㄣ ㄋㄨㄢˇ ㄉㄜ˙", "wash(v.)洗滌 ㄒㄧˇ ㄉㄧˊ", "watch(n./v.)手錶 ㄕㄡˇ ㄅㄧㄠˇ；觀看 ㄍㄨㄢ ㄎㄢˋ", "water(n.)水 ㄕㄨㄟˇ", "way(n.)路 ㄌㄨˋ；方法 ㄈㄤ ㄈㄚˇ", "weak(adj.)虛弱的 ㄒㄩ ㄖㄨㄛˋ ㄉㄜ˙", "wear(v.)穿著 ㄔㄨㄢ ㄓㄨㄛˊ", "weather(n.)天氣 ㄊㄧㄢ ㄑㄧˋ")
            "X" -> listOf("x-ray(n.)X光 X ㄍㄨㄤ", "xenophobia(n.)仇外 ㄔㄡˊ ㄨㄞˋ；排外 ㄆㄞˊ ㄨㄞˋ", "xerox(v./n.)影印 ㄧㄥˇ ㄧㄣˋ", "xylophone(n.)木琴 ㄇㄨˋ ㄑㄧㄣˊ", "xenon(n.)氙 ㄒㄧㄢ（化學元素 ㄏㄨㄚˋ ㄒㄩㄝˊ ㄩㄢˊ ㄙㄨˋ）", "x-axis(n.)X軸 X ㄓㄡˊ", "xenial(adj.)好客的 ㄏㄠˇ ㄎㄜˋ ㄉㄜ˙，友好的 ㄧㄡˇ ㄏㄠˇ ㄉㄜ˙（罕用 ㄏㄢˇ ㄩㄥˋ）", "xylem(n.)木質部 ㄇㄨˋ ㄓˊ ㄅㄨˋ（常見於科學課本 ㄔㄤˊ ㄐㄧㄢˋ ㄩˊ ㄎㄜ ㄒㄩㄝˊ ㄎㄜˋ ㄅㄣˇ）", "xerophyte(n.)耐旱植物 ㄋㄞˋ ㄏㄢˋ ㄓˊ ㄨˋ", "xenomorph(n.)異形生物 ㄧˋ ㄒㄧㄥˊ ㄕㄥ ㄨˋ（常見於電影、遊戲 ㄔㄤˊ ㄐㄧㄢˋ ㄩˊ ㄉㄧㄢˋ ㄧㄥˇ ㄧㄡˊ ㄒㄧˋ）", "xeroma(n.)乾眼症 ㄍㄢ ㄧㄢˇ ㄓㄥˋ", "xanthophyll(n.)葉黃素 ㄧㄝˋ ㄏㄨㄤˊ ㄙㄨˋ", "xenophile(n.)喜歡外國文化者 ㄒㄧˇ ㄏㄨㄢ ㄨㄞˋ ㄍㄨㄛˊ ㄨㄣˊ ㄏㄨㄚˋ ㄓㄜˇ", "xenovirus(n.)外來病毒 ㄨㄞˋ ㄌㄞˊ ㄅㄧㄥˋ ㄉㄨˊ", "xenolith(n.)異質岩 ㄧˋ ㄓˊ ㄧㄢˊ")
            "Y" -> listOf("yard(n.)院子 ㄩㄢˋ ㄗˇ", "yam(n.)山藥 ㄕㄢ ㄧㄠˋ", "year(n.)年 ㄋㄧㄢˊ，一年 ㄧˋ ㄋㄧㄢˊ", "yellow(adj./n.)黃色 ㄏㄨㄤˊ ㄙㄜˋ", "yes(adv.)是的 ㄕˋ ㄉㄜ˙", "yesterday(n./adv.)昨天 ㄗㄨㄛˊ ㄊㄧㄢ", "yet(adv.)尚未 ㄕㄤˋ ㄨㄟˋ；仍然 ㄖㄥˊ ㄖㄢˊ", "you(pron.)你 ㄋㄧˇ；你們 ㄋㄧˇ ㄇㄣˊ", "young(adj.)年輕的 ㄋㄧㄢˊ ㄑㄧㄥ ㄉㄜ˙", "yard(n.)院子 ㄩㄢˋ ㄗˇ", "yawn(v./n.)打哈欠 ㄉㄚˇ ㄏㄚ ㄑㄧㄢˋ", "yell(v./n.)大叫 ㄉㄚˋ ㄐㄧㄠˋ；喊叫 ㄏㄢˇ ㄐㄧㄠˋ", "yummy(adj.)好吃的 ㄏㄠˇ ㄔ ㄉㄜ˙", "yoga(n.)瑜珈 ㄩˊ ㄐㄧㄚ", "yogurt(n.)優格 ㄧㄡ ㄍㄜˊ")
            "Z" -> listOf("zoo(n.)動物園 ㄉㄨㄥˋ ㄨˋ ㄩㄢˊ", "zebra(n.)斑馬 ㄅㄢ ㄇㄚˇ", "zero(n.)零 ㄌㄧㄥˊ", "zip(n./v.)拉鍊 ㄌㄚ ㄌㄧㄢˋ；拉上拉鍊 ㄌㄚ ㄕㄤˋ ㄌㄚ ㄌㄧㄢˋ", "zenith(n.)頂點 ㄉㄧㄥˇ ㄉㄧㄢˇ；最高點 ㄗㄨㄟˋ ㄍㄠ ㄉㄧㄢˇ", "zodiac(n.)黃道帶 ㄏㄨㄤˊ ㄉㄠˋ ㄉㄞˋ；星座 ㄒㄧㄥ ㄗㄨㄛˋ", "zoom(v./n.)快速移動 ㄎㄨㄞˋ ㄙㄨˋ ㄧˊ ㄉㄨㄥˋ；放大鏡頭 ㄈㄤˋ ㄉㄚˋ ㄐㄧㄥˋ ㄊㄡˊ", "zucchini(n.)櫛瓜 ㄓˋ ㄍㄨㄚ", "zone(n.)區域 ㄑㄩ ㄩˋ", "zeal(n.)熱心 ㄖㄜˋ ㄒㄧㄣ；熱情 ㄖㄜˋ ㄑㄧㄥˊ", "zen(n.)禪 ㄔㄢˊ；禪宗 ㄔㄢˊ ㄗㄨㄥ", "zigzag(n./adj.)鋸齒形 ㄐㄩˋ ㄔˇ ㄒㄧㄥˊ；曲折的 ㄑㄩ ㄓㄜˊ ㄉㄜ˙", "zipper(n.)拉鍊 ㄌㄚ ㄌㄧㄢˋ", "zinc(n.)鋅 ㄒㄧㄣ（金屬元素 ㄐㄧㄣ ㄕㄨˇ ㄩㄢˊ ㄙㄨˋ）", "zesty(adj.)充滿熱情的 ㄔㄨㄥ ㄇㄢˇ ㄖㄜˋ ㄑㄧㄥˊ ㄉㄜ˙；有風味的 ㄧㄡˇ ㄈㄥ ㄨㄟˋ ㄉㄜ˙")

            else -> listOf("No words found for $letter")
        }
    }
}
