package com.leonel.upaxapp.ui.imagestore

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.leonel.upaxapp.R
import com.leonel.upaxapp.databinding.FragmentImageStoreBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream


@AndroidEntryPoint
class ImageStoreFragment : Fragment() {

    private var _binding: FragmentImageStoreBinding? = null
    private var imageUri: Uri? = null


    val REQUEST_IMAGE_CAPTURE = 1

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val imagestoreViewModelViewModel =
            ViewModelProvider(this).get(ImageStoreViewModel::class.java)

        _binding = FragmentImageStoreBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.imgsubir.setImageResource(R.drawable.ic_picture_icon)
        binding.imgsubir.setTag("default")

        binding.btnGaleriaStore.setOnClickListener(View.OnClickListener {
            cargarImg()

        })
        binding.btnSubirStore.setOnClickListener(View.OnClickListener {
            if(imageUri!=null || binding.imgsubir.tag !="default") {
                imagestoreViewModelViewModel.enviarImg(imageUri)
            }
            else
                Toast.makeText(activity, "Seleccione una imagen", Toast.LENGTH_SHORT).show()
        })

        binding.btnCamerastore.setOnClickListener(View.OnClickListener {
            //camera()
            val appPerms = arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,

            )
            activityResultLauncher.launch(appPerms)

        })

        imagestoreViewModelViewModel.isLoading.observe(viewLifecycleOwner){
            binding.loadingimg.isVisible = it
            if (it == false) {
                binding.imgsubir.setImageResource(R.drawable.ic_picture_icon)
                Toast.makeText(activity, "Imagen subida con exito", Toast.LENGTH_SHORT).show()
                binding.imgsubir.setTag("default")
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun cargarImg(){
        val pickImage = 100
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        gallery.setType("image/*")
        startActivityForResult(gallery, pickImage)
    }

    fun camera()
    {

        val packageManager = requireActivity().packageManager
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == 100) {
            imageUri = data?.data
            binding.imgsubir.setImageURI(imageUri)
            binding.imgsubir.setTag("send")
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == AppCompatActivity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap

            val bytes = ByteArrayOutputStream()
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
            val path: String = MediaStore.Images.Media.insertImage(
                requireActivity().contentResolver,
                imageBitmap,
                "imagecamera",
                null
            )
            imageUri = Uri.parse(path)


            binding.imgsubir.setImageURI(imageUri)
            binding.imgsubir.setTag("send")
        }
    }
//Permisos


    private var activityResultLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()) {result ->
            var allAreGranted = true
            for(b in result.values) {
                allAreGranted = allAreGranted && b
            }

            if(allAreGranted) {
                camera()
            }
        }


    //***************


}