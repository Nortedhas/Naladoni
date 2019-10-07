package com.example.ageone.Application.Coordinator.Flow.Regular


import android.content.Intent
import androidx.core.view.size
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Router.DataFlow
import com.example.ageone.Application.coordinator
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.intent
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.MainStock.MainStockModel
import com.example.ageone.Modules.MainStock.MainStockView
import com.example.ageone.Modules.MainStock.MainStockViewModel
import com.example.ageone.Modules.Navigation.NavigationModel
import com.example.ageone.Modules.Navigation.NavigationView
import com.example.ageone.Modules.Navigation.NavigationViewModel
import com.example.ageone.R

fun FlowCoordinator.runFlowMainStock(previousFlow: BaseFlow) {

    var flow: FlowMainStock? = FlowMainStock(previousFlow)

    flow?.let{ flow ->

        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)

    }

    flow?.onFinish = {
        viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }

    flow?.start()

}

class FlowMainStock(previousFlow: BaseFlow? = null) : BaseFlow() {

    private var models = FlowMainStockModels()

    init {
        this.previousFlow = previousFlow
    }

    override fun start() {
        onStarted()
        runModuleMainStock()
    }

    inner class FlowMainStockModels {
        var modelMainStock = MainStockModel()
    }

    fun runModuleMainStock() {
        val module = MainStockView(
            InitModuleUI(
                isBottomNavigationVisible = false,

                exitListener = {
                    val text = "Hi"
                    intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, text)
                        type = "text/plain"
                    }
                    currentActivity?.startActivity(Intent.createChooser(intent, null))
                },

                exitIcon = R.drawable.ic_share,

                isBackPressed = true
            )
        )

        module.viewModel.initialize(models.modelMainStock) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when (MainStockViewModel.EventType.valueOf(event)) {
                MainStockViewModel.EventType.OnlouderMainStock -> {
                    coordinator.runFlowNavigation(this)
                }
            }
        }
        push(module)
    }

}