package com.example.ageone.Modules.Pleer

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Handler
import android.view.Gravity
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.annotation.Nullable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.example.ageone.Application.*
import com.example.ageone.Application.R
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.SeekBar.BaseSeekBar
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.InitModuleUI
import com.example.ageone.External.RxBus.RxBus
import com.example.ageone.External.RxBus.RxEvent
import com.example.ageone.Modules.PleerViewModel
import io.reactivex.disposables.Disposable
import timber.log.Timber
import yummypets.com.stevia.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.schedule


class PleerView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = PleerViewModel()

    val textView by lazy {
        val textView = BaseTextView()
        textView.text = "Включить звук на фон"
        textView.textColor = Color.WHITE
        textView.textSize = 17F
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    val imageView1 by lazy {
        val imageView = BaseImageView()
        imageView.cornerRadius = (utils.variable.displayWidth * 46 / 375).dp
        imageView.setImageResource(R.drawable.pic_music1)
        imageView.setPadding(2.dp,2.dp,2.dp,2.dp)
        imageView.initialize()
        imageView
    }

    val textViewNamed1 by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.WHITE
        textView.textSize = 11F
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.text = "Капли дождя"
        textView
    }

    val imageView2 by lazy {
        val imageView = BaseImageView()
        imageView.cornerRadius = utils.variable.displayWidth * 46 / 375.dp
        imageView.setImageResource(R.drawable.pic_music2)
        imageView.setPadding(2.dp,2.dp,2.dp,2.dp)
        imageView.initialize()
        imageView
    }

    val textViewNamed2 by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.WHITE
        textView.textSize = 11F
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.text = "Шум ветра"
        textView
    }

    val imageView3 by lazy {
        val imageView = BaseImageView()
        imageView.cornerRadius = utils.variable.displayWidth * 46 / 375.dp
        imageView.setImageResource(R.drawable.pic_music3)
        imageView.setPadding(2.dp,2.dp,2.dp,2.dp)
        imageView.initialize()
        imageView
    }

    val textViewNamed3 by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.WHITE
        textView.textSize = 11F
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.text = "Треск камина"
        textView
    }

    val imageView4 by lazy {
        val imageView = BaseImageView()
        imageView.cornerRadius = utils.variable.displayWidth * 46 / 375.dp
        imageView.setImageResource(R.drawable.pic_music4)
        imageView.setPadding(2.dp,2.dp,2.dp,2.dp)
        imageView.initialize()
        imageView
    }

    val textViewNamed4 by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.WHITE
        textView.textSize = 11F
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.text = "Под водой"
        textView
    }

    val textViewSound by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.WHITE
        textView.textSize = 13F
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.text = "Громкость фонового звука"
        textView
    }

    val textViewName by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.WHITE
        textView.textSize = 27F
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.text = "Дыхание природы"
        textView
    }

    val textViewDescribe by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.WHITE
        textView.textSize = 17F
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.text = "Не следует, однако забывать, что рамки и место обучения."
        textView
    }

    val seekBarSound by lazy {
        val view = BaseSeekBar()
        view.setBackgroundColor(Color.TRANSPARENT)
        view.colorThumb = Color.WHITE
        view.colorProgressLine = Color.WHITE
        view.initialize()
        view
    }

    val seekBarMeditation by lazy {
        val view = BaseSeekBar()
        view.setBackgroundColor(Color.TRANSPARENT)
        view.colorThumb = Color.WHITE
        view.colorProgressLine = Color.WHITE
        view.initialize()
        view
    }

    val time = SimpleDateFormat("mm:ss")

    val textViewCurrentTimeMeditation by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.WHITE
        textView.textSize = 11F
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    val textViewLeftTimeMeditation by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.WHITE
        textView.textSize = 11F
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    var isPlay = true
    val viewButton by lazy {
        val view = BaseImageView()
        view.setBackgroundResource(R.drawable.button_play)
        view
    }

    private var durationDisposable: Disposable
    private var currentTimeDisposable: Disposable
    private var meditationPlayingEndDisposable: Disposable
    var currentTime: Long = 0
    var isSeekMoving = false
    private lateinit var timer: Timer


    init {

        val placeholderImage = GradientDrawable()
        placeholderImage.setColor(Color.GRAY)

        background = placeholderImage

        Glide.with(this)
            .load(rxData.currentMeditation?.image?.original ?: R.drawable.back_pleer)
            .fitCenter()
            .placeholder(placeholderImage)
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: com.bumptech.glide.request.transition.Transition<in Drawable>?
                ) {
                    background = resource
                }

                override fun onLoadCleared(@Nullable placeholder: Drawable?) {}

            })

        toolbar.title = "Прослушивание"
        renderToolbar()

        durationDisposable = RxBus.listen(RxEvent.EventChangeDuration::class.java).subscribe { event ->
            Timber.i("event change duration ${event.duration}")
            setDuration(event.duration)
            viewModel.model.duration = event.duration
        }

        meditationPlayingEndDisposable = RxBus.listen(RxEvent.EventMediaPlayerEnd::class.java).subscribe {
            timer.cancel()
            viewButton.setBackgroundResource(R.drawable.button_play)
            isPlay = true

            Timber.i("event player stop")
            currentTime = 0L
            setCurrentTime(0L)
            setDuration(viewModel.model.duration)
        }

        seekBarMeditation.setOnSeekBarChangeListener(onSeekBarMeditationChangeListener())
        seekBarSound.setOnSeekBarChangeListener(onSeekBarBackgroundSoundChangeListener())

        currentTimeDisposable = RxBus.listen(RxEvent.EventChangeCurrentTime::class.java).subscribe { event ->
            Timber.i("event change current time ${event.currentTime}")
            currentTime = event.currentTime
            setCurrentTime(event.currentTime)
        }

        imageView1.setOnClickListener {
            onClickImage(0)
        }
        imageView2.setOnClickListener {
            onClickImage(1)
        }
        imageView3.setOnClickListener {
            onClickImage(2)
        }
        imageView4.setOnClickListener {
            onClickImage(3)
        }

        onClickImage(rxData.currentBackground)

        viewButton.setOnClickListener {
            onClickButtonPLay()
        }


        renderUIO()

    }

    override fun unBind() {

    }

    private fun onSeekBarMeditationChangeListener(): OnSeekBarChangeListener {
        return object : OnSeekBarChangeListener {

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                Timber.i("Stop Seek")
                rxData.currentTime = (seekBar.progress / 100.0 * viewModel.model.duration).toLong()
                isSeekMoving = false

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                isSeekMoving = true
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

                Timber.i("Change Seek $progress")

            }
        }
    }

    private fun onSeekBarBackgroundSoundChangeListener(): OnSeekBarChangeListener {
        return object : OnSeekBarChangeListener {

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                Timber.i("Stop Seek")
                rxData.volumeBackground = seekBar.progress / 100F

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            }
        }
    }

    //on click play\stop button
    private fun onClickButtonPLay() {
        if (isPlay){
            viewButton.setBackgroundResource(R.drawable.button_stop)

            timer = Timer()
            timer.schedule(0, 1000){
                currentTime += 1000
                currentActivity?.runOnUiThread {
                    setCurrentTime(currentTime)
                    setDuration()
                }
            }
        } else {
            timer.cancel()
            viewButton.setBackgroundResource(R.drawable.button_play)

//            currentActivity?.stopService(Intent(currentActivity, MusicService::class.java))
        }

        isPlay = !isPlay
    }

    //add stroke to selected image (background sound)
    private fun onClickImage(selected: Int) {
        rxData.currentBackground = selected
        val imageViews = arrayOf(imageView1, imageView2, imageView3, imageView4)

        val selectedColor = Color.parseColor("#8863E6")
        val selectedStroke = GradientDrawable()
        selectedStroke.setColor(selectedColor)
        selectedStroke.shape = GradientDrawable.OVAL

        for (i in imageViews.indices) {
            if (selected == i) {
                imageViews[i].background = selectedStroke
            } else {
                imageViews[i].background = null
            }
        }

    }
}

fun PleerView.renderUIO() {

    innerContent.subviews(
        textView,
        imageView1,
        textViewNamed1,
        imageView2,
        textViewNamed2,
        imageView3,
        textViewNamed3,
        imageView4,
        textViewNamed4,
        textViewSound,
        seekBarSound,
        textViewName,
        textViewDescribe,
        seekBarMeditation,
        textViewCurrentTimeMeditation,
        textViewLeftTimeMeditation,
        viewButton
    )

    textView
        .fillHorizontally()
        .constrainTopToTopOf(innerContent, utils.variable.displayWidth * 8 / 375)

    val size = utils.variable.displayWidth * 46 / 375
    val topMarginImage = utils.variable.displayWidth * 24 / 375
    val spaceMargin = utils.variable.displayWidth * 38 / 375
    val topMarginText = utils.variable.displayWidth * 8 / 375

    imageView1
        .width(size)
        .height(size)
        .constrainTopToBottomOf(textView, topMarginImage)
        .constrainLeftToLeftOf(innerContent, spaceMargin)

    textViewNamed1
        .width(size)
        .constrainTopToBottomOf(imageView1, topMarginText)
        .constrainLeftToLeftOf(imageView1)

    imageView2
        .width(size)
        .height(size)
        .constrainTopToBottomOf(textView, topMarginImage)
        .constrainLeftToRightOf(imageView1, spaceMargin)

    textViewNamed2
        .width(size)
        .constrainTopToBottomOf(imageView2, topMarginText)
        .constrainLeftToLeftOf(imageView2)

    imageView3
        .width(size)
        .height(size)
        .constrainTopToBottomOf(textView, topMarginImage)
        .constrainLeftToRightOf(imageView2, spaceMargin)

    textViewNamed3
        .width(size)
        .constrainTopToBottomOf(imageView3, topMarginText)
        .constrainLeftToLeftOf(imageView3)

    imageView4
        .width(size)
        .height(size)
        .constrainTopToBottomOf(textView, topMarginImage)
        .constrainLeftToRightOf(imageView3, spaceMargin)

    textViewNamed4
        .width(size)
        .constrainTopToBottomOf(imageView4, topMarginText)
        .constrainLeftToLeftOf(imageView4)

    textViewSound
        .fillHorizontally()
        .constrainTopToBottomOf(textViewNamed1, utils.variable.displayWidth * 20 / 375)

    seekBarSound
        .width(utils.variable.displayWidth * 257 / 375)
        .fillHorizontally(utils.variable.displayWidth * 55 / 375)
        .constrainTopToBottomOf(textViewSound, utils.variable.displayWidth * 15 / 375)

    textViewLeftTimeMeditation
        .constrainTopToBottomOf(seekBarMeditation, 8)
        .constrainRightToRightOf(seekBarMeditation, 8)

    textViewCurrentTimeMeditation
        .constrainTopToBottomOf(seekBarMeditation, 8)
        .constrainLeftToLeftOf(seekBarMeditation, 8)

    textViewName
        .fillHorizontally(8)
        .constrainBottomToTopOf(textViewDescribe, utils.variable.displayWidth * 8 / 375)

    textViewDescribe
        .fillHorizontally(utils.variable.displayWidth * 50 / 375)
        .constrainBottomToTopOf(seekBarMeditation, utils.variable.displayWidth * 15 / 375)

    seekBarMeditation
        .fillHorizontally(8)
        .constrainBottomToTopOf(viewButton, utils.variable.displayWidth * 45 / 375)

    textViewCurrentTimeMeditation.text = time.format(Date(0))
    textViewLeftTimeMeditation.text = time.format(Date(0))


    val sizeButton = utils.variable.displayWidth * 85 / 375
    viewButton
        .width(sizeButton)
        .height(sizeButton)
        .constrainRightToRightOf(innerContent)
        .constrainLeftToLeftOf(innerContent)
        .constrainBottomToBottomOf(innerContent, utils.variable.displayWidth * 45 / 375)
}

fun PleerView.setDuration() {
    val left = viewModel.model.duration - currentTime
    Timber.i("Duration Seek: ${viewModel.model.duration} current time: $currentTime")
    textViewLeftTimeMeditation.text =
        if (viewModel.model.duration <= currentTime) time.format(Date(0))
        else "-${time.format(Date(left))}"
}
fun PleerView.setDuration(millisec: Long) {
    Timber.i("Duration Seek: ${viewModel.model.duration} millisec: $millisec")
    textViewLeftTimeMeditation.text = time.format(Date(millisec))
}

fun PleerView.setCurrentTime(millisec: Long) {

    textViewCurrentTimeMeditation.text = time.format(Date(millisec))

    if(!isSeekMoving) {
        val progress: Int = if (viewModel.model.duration == 0L) 0
            else ((millisec / 1000).toDouble() / (viewModel.model.duration / 1000).toDouble() * 100).toInt()
        seekBarMeditation.progress = progress
    }
}

