package basicconsoleapp.rmi

/**
 * Created by jinzhanga on 2018/8/9.
 */

interface SpitterService {

    fun getRencentSpitters(count: Int): List<Spitter>

    fun saveSpitter(spitter: Spitter)

    fun getSpitter(id: Long): Spitter

}