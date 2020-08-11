package cz.covid19cz.erouska.ui.sandbox

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import cz.covid19cz.erouska.R
import cz.covid19cz.erouska.databinding.FragmentSandboxBinding
import cz.covid19cz.erouska.ui.base.BaseFragment
import cz.covid19cz.erouska.ui.dashboard.DashboardFragment
import cz.covid19cz.erouska.ui.dashboard.event.GmsApiErrorEvent

class SandboxFragment : BaseFragment<FragmentSandboxBinding, SandboxVM>(R.layout.fragment_sandbox, SandboxVM::class) {

    companion object{
        const val REQUEST_GMS_ERROR_RESOLUTION = 42
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribe(GmsApiErrorEvent::class){
            startIntentSenderForResult(it.status.resolution?.intentSender,
                REQUEST_GMS_ERROR_RESOLUTION, null, 0, 0, 0, null)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_GMS_ERROR_RESOLUTION && resultCode == Activity.RESULT_OK){
            viewModel.refreshTeks()
        }
    }
}