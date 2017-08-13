package bootapp.web;


import bootapp.dao.PersonRepository;
import bootapp.demain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }


    @RequestMapping("/save")
    public Person save(String name, String address, Integer age) {
        return personRepository.save(new Person(null, name, age, address));
    }

    @RequestMapping("/q1")
    public List<Person> q1(String address) {
        return personRepository.findByAddress(address);
    }

}
