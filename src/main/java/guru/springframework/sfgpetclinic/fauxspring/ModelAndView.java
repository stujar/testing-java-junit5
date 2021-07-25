package guru.springframework.sfgpetclinic.fauxspring;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelAndView implements Model{

    private Map<String, Object> map = new HashMap<>();

    public ModelAndView() {
    }

    public ModelAndView(String view) {

    }

    public void addObject(Object o){

    }
    // Implemented Model
    @Override
    public Object getMapObject(String key) {
        return this.map.get(key);
    }

    @Override
    public void addAttribute(String key, Object o) {
        this.map.put(key, o);
    }

    @Override
    public void addAttribute(Object o) {
    }
}
