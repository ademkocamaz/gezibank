package ademkocamaz.gezibank.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class OzelSharedPreferences {

    companion object {
        private val BUTCE="butce"
        private var sharedPreferences: SharedPreferences? = null

        @Volatile
        private var instance: OzelSharedPreferences? = null

        private val lock = Any()
        operator fun invoke(context: Context): OzelSharedPreferences = instance ?: synchronized(lock){
            instance ?: ozelSharedPreferencesYap(context).also {
                instance=it
            }
        }

        private fun ozelSharedPreferencesYap(context: Context):OzelSharedPreferences{
            sharedPreferences=androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            return OzelSharedPreferences()

        }
    }

    fun butceKaydet(butce:Float){
        sharedPreferences?.edit(commit=true){
            putFloat(BUTCE,butce)
        }
    }

    fun butceAl()= sharedPreferences?.getFloat(BUTCE,0f)

}