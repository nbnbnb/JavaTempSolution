package basicconsoleapp.rmi

import org.springframework.stereotype.Service

/**
 * Created by jinzhanga on 2018/8/9.
 */

@Service
class SpitterServiceImp : SpitterService {

    override fun getRencentSpitters(count: Int): List<Spitter> {
        return listOf()
    }

    override fun saveSpitter(spitter: Spitter) {
        println("save done")
    }

    override fun getSpitter(id: Long): Spitter {
        val spitter = Spitter()
        spitter.username = "spitter $id"
        spitter.id = id
        println(spitter.username)
        println(spitter)
        return spitter
    }


}