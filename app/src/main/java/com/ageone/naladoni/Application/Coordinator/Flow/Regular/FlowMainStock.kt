package com.ageone.naladoni.Application.Coordinator.Flow.Regular


import android.content.Intent
import androidx.core.view.children
import androidx.core.view.size
import com.ageone.naladoni.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.naladoni.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.naladoni.Application.Coordinator.Router.DataFlow
import com.ageone.naladoni.Application.coordinator
import com.ageone.naladoni.Application.currentActivity
import com.ageone.naladoni.Application.intent
import com.ageone.naladoni.External.Base.Flow.BaseFlow
import com.ageone.naladoni.External.Base.Module.Module
import com.ageone.naladoni.External.Icon
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.Modules.MainStock.MainStockModel
import com.ageone.naladoni.Modules.MainStock.MainStockView
import com.ageone.naladoni.Modules.MainStock.MainStockViewModel
import com.ageone.naladoni.R
import timber.log.Timber

fun FlowCoordinator.runFlowMainStock(previousFlow: BaseFlow) {

    var flow: FlowMainStock? = FlowMainStock(previousFlow)

    flow?.let{ flow ->

        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)

    }

    flow?.onFinish = {

        flow?.viewFlipperModule?.children?.forEachIndexed { index, view ->
            if (view is Module) {
                Timber.i("Delete module in flow finish")
                view.onDeInit?.invoke()
            }
        }

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
                isBackPressed = true,
                firstIcon = Icon(
                    icon = R.drawable.ic_share,
                    listener = {
                        val text = "Hi"
                        intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, text)
                            type = "text/plain"
                        }
                        currentActivity?.startActivity(Intent.createChooser(intent, null))
                    }
                )
            )
        )

        module.viewModel.initialize(models.modelMainStock) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when (MainStockViewModel.EventType.valueOf(event)) {
                MainStockViewModel.EventType.OnClickMainStock -> {
                    coordinator.runFlowNavigation(this)
                }
            }
        }
        push(module)
    }

}