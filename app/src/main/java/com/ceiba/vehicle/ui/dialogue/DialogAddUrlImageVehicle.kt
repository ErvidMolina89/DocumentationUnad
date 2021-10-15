package com.ceiba.vehicle.ui.dialogue

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.ceiba.vehicle.R

class DialogAddUrlImageVehicle  private constructor()
    : DialogFragment() {

    companion object{
        private var showingDialog = false
        @SuppressLint("StaticFieldLeak")
        private var instance : DialogAddUrlImageVehicle?= null
        private var textUrl : String = ""

        fun getInstance() : DialogAddUrlImageVehicle {
            if(instance == null ) {
                instance = DialogAddUrlImageVehicle()
            }
            return instance!!
        }
    }

    private var image_dialogue_cost : ImageView?= null
    private var details_info : TextView?= null
    private var editTextImage: EditText? = null
    private var btn_ok : Button?= null
    private var btn_cancel : Button?= null

    private var mainContainer : View?= null
    private var invokesActionOk:((String)->Unit)?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mainContainer = inflater.inflate(R.layout.dialog_image_vehicle,null,false)
        isCancelable = false

        findsViewElements()
        addListeners()
        return mainContainer
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findsViewElements()
        addListeners()

    }

    private fun findsViewElements(){

        image_dialogue_cost = mainContainer?.findViewById(R.id.image_dialogue)
        editTextImage     = mainContainer?.findViewById(R.id.editTextImageVehicle)
        details_info        = mainContainer?.findViewById(R.id.details_mess_info)
        btn_ok              = mainContainer?.findViewById(R.id.btnAcceptDialogue)
        btn_cancel          = mainContainer?.findViewById(R.id.btnCancelDialogue)

    }

    private fun addListeners(){
        btn_cancel?.setOnClickListener {
            dismiss()
        }

        btn_ok?.setOnClickListener {
            textUrl = editTextImage?.text.toString()
            dismiss()
            invokesActionOk?.invoke(textUrl)
            cleanElementsOfSight()
        }
    }

    private fun cleanElementsOfSight(){
        invokesActionOk = null
        editTextImage = null
    }

    override fun dismiss() {
        showingDialog = false
        if(fragmentManager == null ){
            return
        }
        super.dismiss()
        super.dismissAllowingStateLoss()
    }

    override fun onStart() {
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        super.onStart()
    }

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            if(showingDialog){ return }
            if(isAdded){ return }
            showingDialog = true
            super.show(manager, tag)
        }catch (e : Exception) {
            e.printStackTrace()
        }
    }

    fun withActionBtnOk(actionOk : (String)->Unit) : DialogAddUrlImageVehicle {
        this.invokesActionOk = actionOk
        return this
    }

    fun showDialogue(fragmentManager: FragmentManager, etiqueta : String){
        show(fragmentManager,etiqueta)
    }

}