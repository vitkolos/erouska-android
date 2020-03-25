package cz.covid19cz.app.ui.confirm

import cz.covid19cz.app.R
import cz.covid19cz.app.service.CovidService
import cz.covid19cz.app.utils.Auth

class DeleteUserFragment : ConfirmationFragment() {
    override val description by lazy { getString(R.string.delete_user_desc, Auth.getPhoneNumber())}
    override val buttonTextRes = R.string.delete_registration

    override fun confirmedClicked() {
        context?.let {
            it.startService(CovidService.stopService(it))
        }
        viewModel.deleteUser()
    }

    override fun doWhenFinished() {
        navController().navigate(R.id.action_deleteUserFragment_to_nav_welcome_fragment)
    }
}