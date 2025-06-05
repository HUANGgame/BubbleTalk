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

class ThemeWordsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LetterWordsAdapter
    private lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_letter_words)
        tts = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {

                val result = tts.setLanguage(Locale.getDefault())

            }
        }
        // 返回
        val btnBack: Button = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            showImageThenFinish()
        }

        val theme = intent.getStringExtra("theme") ?: "日常生活"
        val words = getWordsForTheme(theme)

        recyclerView = findViewById(R.id.recyclerViewWords)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = LetterWordsAdapter(words, tts)
        recyclerView.adapter = adapter
    }

    override fun onBackPressed() {
        showImageThenFinish()
    }
    private fun showImageThenFinish() {

        val dialog = Dialog(this)

        dialog.setContentView(R.layout.dialog_image)

        dialog.setCancelable(false)
        dialog.show()


        Handler(Looper.getMainLooper()).postDelayed({
            dialog.dismiss()
            finish()
        }, 2000)
    }


    private fun getWordsForTheme(theme: String): List<String> {
        return when (theme) {
            "日常生活daily" -> listOf(
                "brush(n.)刷子 ㄕㄨㄚ ㄗ˙", "wake(v.)醒來 ㄒㄧㄥˇ ㄌㄞˊ", "eat(v.)吃 ㄔ", "walk(v.)走路 ㄗㄡˇ ㄌㄨˋ",
                "talk(v.)講話 ㄐㄧㄤˇ ㄏㄨㄚˋ", "read(v.)閱讀 ㄩˋ ㄉㄨˊ", "write(v.)寫 ㄒㄧㄝˇ", "play(v.)玩 ㄨㄢˊ",
                "cook(v.)烹飪 ㄆㄥ ㄖㄣˋ", "clean(v.)清潔 ㄑㄧㄥ ㄐㄧㄝˊ"
            )
            "服裝clothes" -> listOf(
                "shirt(n.)襯衫 ㄔㄣˋ ㄕㄢ", "pants(n.)長褲 ㄔㄤˊ ㄎㄨˋ", "dress(n.)洋裝 ㄧㄤˊ ㄓㄨㄤ", "hat(n.)帽子 ㄇㄠˋ ㄗ˙",
                "jacket(n.)外套 ㄨㄞˋ ㄊㄠˋ", "shoes(n.)鞋子 ㄒㄧㄝˊ ㄗ˙", "scarf(n.)圍巾 ㄨㄟˊ ㄐㄧㄣ", "skirt(n.)裙子 ㄑㄩㄣˊ ㄗ˙",
                "socks(n.)襪子 ㄨㄚˋ ㄗ˙", "belt(n.)皮帶 ㄆㄧˊ ㄉㄞˋ"
            )
            "天氣weather" -> listOf(
                "sunny(adj.)晴朗的 ㄑㄧㄥˊ ㄌㄤˇ ㄉㄜ˙", "cloudy(adj.)多雲的 ㄉㄨㄛ ㄩㄣˊ ㄉㄜ˙", "rainy(adj.)下雨的 ㄒㄧㄚˋ ㄩˇ ㄉㄜ˙",
                "windy(adj.)有風的 ㄧㄡˇ ㄈㄥ ㄉㄜ˙", "snowy(adj.)下雪的 ㄒㄧㄚˋ ㄒㄩㄝˇ ㄉㄜ˙", "foggy(adj.)有霧的 ㄧㄡˇ ㄨˋ ㄉㄜ˙",
                "stormy(adj.)暴風雨的 ㄅㄠˋ ㄈㄥ ㄩˇ ㄉㄜ˙", "hot(adj.)熱的 ㄖㄜˋ ㄉㄜ˙", "cold(adj.)冷的 ㄌㄥˇ ㄉㄜ˙", "warm(adj.)溫暖的 ㄨㄣ ㄋㄨㄢˇ ㄉㄜ˙"
            )
            "家庭family" -> listOf(
                "father(n.)父親 ㄈㄨˋ ㄑㄧㄣ", "mother(n.)母親 ㄇㄨˇ ㄑㄧㄣ", "brother(n.)兄弟 ㄒㄩㄥ ㄉㄧˋ", "sister(n.)姊妹 ㄗˇ ㄇㄟˋ",
                "grandfather(n.)爺爺 ㄧㄝˊ ㄧㄝ", "grandmother(n.)奶奶 ㄋㄞˇ ㄋㄞ˙", "uncle(n.)叔叔 ㄕㄨˊ ㄕㄨ˙",
                "aunt(n.)阿姨 ㄚ ㄧˊ", "cousin(n.)表兄弟姊妹 ㄅㄧㄠˇ ㄒㄩㄥ ㄉㄧˋ ㄗˇ ㄇㄟˋ", "baby(n.)嬰兒 ㄧㄥ ㄦˊ"
            )
            "學校school" -> listOf(
                "pencil(n.)鉛筆 ㄑㄧㄢ ㄅㄧˇ", "pen(n.)原子筆 ㄩㄢˊ ㄗˇ ㄅㄧˇ", "eraser(n.)橡皮擦 ㄒㄧㄤˋ ㄆㄧˊ ㄘㄚ", "book(n.)書 ㄕㄨ",
                "desk(n.)書桌 ㄕㄨ ㄓㄨㄛ", "chair(n.)椅子 ㄧˇ ㄗ˙", "blackboard(n.)黑板 ㄏㄟ ㄅㄢˇ", "notebook(n.)筆記本 ㄅㄧˇ ㄐㄧˋ ㄅㄣˇ",
                "ruler(n.)尺 ㄔˇ", "bag(n.)書包 ㄕㄨ ㄅㄠ"
            )
            "日期date" -> listOf(
                "Monday(n.)星期一 ㄒㄧㄥ ㄑㄧ ㄧ", "Tuesday(n.)星期二 ㄒㄧㄥ ㄑㄧ ㄦˋ", "Wednesday(n.)星期三 ㄒㄧㄥ ㄑㄧ ㄙㄢ", "Thursday(n.)星期四 ㄒㄧㄥ ㄑㄧ ㄙˋ",
                "Friday(n.)星期五 ㄒㄧㄥ ㄑㄧ ㄨˇ", "Saturday(n.)星期六 ㄒㄧㄥ ㄑㄧ ㄌㄧㄡˋ", "Sunday(n.)星期日 ㄒㄧㄥ ㄑㄧ ㄖˋ", "week(n.)週 ㄓㄡ",
                "month(n.)月份 ㄩㄝˋ ㄈㄣ", "year(n.)年 ㄋㄧㄢˊ"
            )
            "食物food" -> listOf(
                "apple(n.)蘋果 ㄆㄧㄥˊ ㄍㄨㄛˇ", "banana(n.)香蕉 ㄒㄧㄤ ㄐㄠ", "bread(n.)麵包 ㄇㄧㄢˋ ㄅㄠ", "milk(n.)牛奶 ㄋㄧㄡˊ ㄋㄞˇ",
                "cheese(n.)起司 ㄑㄧˇ ㄙ", "rice(n.)米飯 ㄇㄧˇ ㄈㄢˋ", "meat(n.)肉 ㄖㄡˋ", "fish(n.)魚 ㄩˊ",
                "egg(n.)雞蛋 ㄐㄧ ㄉㄢˋ", "cake(n.)蛋糕 ㄉㄢˋ ㄍㄠ"
            )
            "顏色color" -> listOf(
                "red(n.)紅色 ㄏㄨㄥˊ ㄙㄜˋ", "blue(n.)藍色 ㄌㄢˊ ㄙㄜˋ", "green(n.)綠色 ㄌㄩˋ ㄙㄜˋ", "yellow(n.)黃色 ㄏㄨㄤˊ ㄙㄜˋ", "black(n.)黑色 ㄏㄟ ㄙㄜˋ",
                "white(n.)白色 ㄅㄞˊ ㄙㄜˋ", "pink(n.)粉紅色 ㄈㄣˇ ㄏㄨㄥˊ ㄙㄜˋ", "brown(n.)棕色 ㄗㄨㄥ ㄙㄜˋ", "orange(n.)橘色 ㄐㄩˊ ㄙㄜˋ", "purple(n.)紫色 ㄗˇ ㄙㄜˋ"
            )
            "動物animal" -> listOf(
                "dog(n.)狗 ㄍㄡˇ", "cat(n.)貓 ㄇㄠ", "fish(n.)魚 ㄩˊ", "bird(n.)鳥 ㄋㄧㄠˇ", "cow(n.)牛 ㄋㄧㄡˊ",
                "pig(n.)豬 ㄓㄨ", "horse(n.)馬 ㄇㄚˇ", "sheep(n.)綿羊 ㄇㄧㄢˊ ㄧㄤˊ", "lion(n.)獅子 ㄕ ㄗ˙", "tiger(n.)老虎 ㄌㄠˇ ㄏㄨˇ"
            )
            "運動exercise" -> listOf(
                "run(v.)跑 ㄆㄠˇ", "jump(v.)跳 ㄊㄧㄠˋ", "swim(v.)游泳 ㄧㄡˊ ㄩㄥˇ", "throw(v.)投擲 ㄊㄡˊ ㄓˋ", "catch(v.)接住 ㄐㄧㄝ ㄓㄨˋ",
                "kick(v.)踢 ㄊㄧ", "cycle(v.)騎腳踏車 ㄑㄧˊ ㄐㄧㄠˇ ㄊㄚˋ ㄔㄜ", "climb(v.)攀爬 ㄆㄢ ㄆㄚˊ", "dive(v.)潛水 ㄑㄧㄢˊ ㄕㄨㄟˇ", "skate(v.)溜冰 ㄌㄧㄡˋ ㄅㄧㄥ"
            )

            else -> listOf("No words found for $theme")
        }
    }

}
