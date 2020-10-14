package br.com.testesicredi.view.dialog

import android.os.Bundle
import android.text.TextUtils
import android.util.DisplayMetrics
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import br.com.testesicredi.R
import br.com.testesicredi.controller.SicrediController
import br.com.testesicredi.model.request.CheckIn
import kotlinx.android.synthetic.main.check_in_dialog_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckInDialogFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val wrapper = ContextThemeWrapper(activity, R.style.AppTheme)
        val localInflater = inflater.cloneInContext(wrapper)
        val root: View = localInflater.inflate(R.layout.check_in_dialog_fragment, container, false)
        buildLayout(root)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (dialog != null && dialog!!.window != null)
            dialog!!.window!!.attributes = getDialogParams(90, 60)
    }

   fun buildLayout(view: View) {
        setBtCheckIn(view)
    }

    fun setBtCheckIn(view: View) {
        val btnCheckIn = view.findViewById<Button>(R.id.btn_check_in)
        btnCheckIn?.setOnClickListener { if (validateFields()) onCheckInClicked() }
    }

    fun onCheckInClicked() {
        SicrediController.getInstance().sicrediService.checkIn(CheckIn(SicrediController.getInstance().eventSelected,
                et_name?.text.toString(), et_email?.text.toString()))?.enqueue(object : Callback<CheckIn?> {
            override fun onResponse(call: Call<CheckIn?>, response: Response<CheckIn?>) {
                dismiss()
            }

            override fun onFailure(call: Call<CheckIn?>, t: Throwable) {
                dismiss()
            }
        })
    }

    fun validateFields(): Boolean {
        val msg = "Preencha seu "
        if (TextUtils.isEmpty(et_name?.text.toString())) {
            Toast.makeText(context, msg + "nome", Toast.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(et_email?.text.toString())) {
            Toast.makeText(context, msg + "email", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun getDialogParams(percentageWidth: Int, percentageHeight: Int): WindowManager.LayoutParams? {
        if (activity != null && dialog != null && dialog!!.window != null) {
            val metrics = DisplayMetrics()
            activity!!.windowManager.defaultDisplay.getMetrics(metrics)
            val params = this.dialog!!.window!!.attributes
            params.height = metrics.heightPixels - metrics.heightPixels * (100 - percentageHeight) / 100
            params.width = metrics.widthPixels - metrics.widthPixels * (100 - percentageWidth) / 100
            return params
        }
        return WindowManager.LayoutParams()
    }

}