package com.boot.webapp4springbootgradle.controller;

import com.boot.webapp4springbootgradle.model.Sample;
import com.boot.webapp4springbootgradle.service.SampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.util.*;

@Controller
public class SampleController {
    @Autowired
    SampleService sampleService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value="/server_time", method = RequestMethod.GET)
    public String getServerTime(Locale locale, Model model) {
        //로직 수행
        logger.debug("1.getServerTime() {}", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        logger.debug("2.ServerTime is " + formattedDate);

        //model객체를 이용해서 view로 Data전달
        model.addAttribute("serverTime", formattedDate);

        return "/sample/server_time";   //뷰 파일 리턴
    }

    @RequestMapping(value="/get_list", method = RequestMethod.GET)
    public String getList(Locale locale, Model model) {
        //로직 수행
        logger.debug("1.getList() {}", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        logger.debug("2.ServerTime is " + formattedDate);

        List<String> list = new ArrayList<>();
        for(int i=1; i<=10; i++) {
            String kang = "강성주는 천재다.-"+i;
            list.add(kang);
        }

        //model객체를 이용해서 view로 Data전달
        model.addAttribute("serverTime", formattedDate);
        model.addAttribute("list", list);

        return "/sample/get_list";   //뷰 파일 리턴
    }

    @RequestMapping(value="/get_list_object", method = RequestMethod.GET)
    public String getListObject(Locale locale, Model model) {
        //로직 수행
        logger.debug("1.getListObject() {}", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        logger.debug("2.ServerTime is " + formattedDate);

        //model객체를 이용해서 view로 Data전달
        model.addAttribute("serverTime", formattedDate);

        return "/sample/get_list_object";   //뷰 파일 리턴
    }


    @RequestMapping(value="/search_member_list.formdata", method = RequestMethod.GET)
    public String searchSample(@RequestParam Map<String, String> param, Locale locale, Model model) {
        //로직 수행
        logger.debug("1.searchSample() {}", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        logger.debug("2.ServerTime is " + formattedDate);

        List<Sample> list = new ArrayList<>();
        String name = param.get("name");
        String address = param.get("address");
//        list = sampleService.findAll();
        list = sampleService.findByNameContainsIgnoreCaseAndAddressContainsIgnoreCase(name, address);

        //model객체를 이용해서 view로 Data전달
        model.addAttribute("serverTime", formattedDate);
        model.addAttribute("list", list);

        return "/sample/get_list_object";   //뷰 파일 리턴
    }

    @RequestMapping(value="/insert_sample", method = RequestMethod.GET)
    public String insertSample(Locale locale, Model model) {
        //로직 수행
        logger.debug("1.insertSample() {}", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        logger.debug("2.ServerTime is " + formattedDate);

        Sample sample = new Sample();


        //model객체를 이용해서 view로 Data전달
        model.addAttribute("serverTime", formattedDate);
        model.addAttribute("sample", sample);

        return "/sample/insert_sample";   //뷰 파일 리턴
    }

    @RequestMapping(value="/insert_sample.formdata", method = RequestMethod.POST)
    public String insertSampleFormdata(@RequestParam Map<String, String> param, Locale locale, Model model) {
        //로직 수행
        logger.debug("1.insertSampleFormdata() {}", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        logger.debug("2.ServerTime is " + formattedDate);

        //STEP-01 : 저장
        String eno = param.get("eno");
        String name = param.get("name");
        String age = param.get("age");
        String address = param.get("address");
        String cellPhone = param.get("cellPhone");

        Sample sample = new Sample();
        sample.eno = Integer.parseInt(eno);
        sample.name = name;
        sample.age = Integer.parseInt(age);
        sample.address = address;
        sample.cellPhone = cellPhone;
        int count = sampleService.save(sample);

        //STEP-02 : 조회
        List<Sample> list = sampleService.findAll();

        //model객체를 이용해서 view로 Data전달
        model.addAttribute("serverTime", formattedDate);
        model.addAttribute("list", list);

        return "/sample/get_list_object";   //뷰 파일 리턴
    }

    @RequestMapping(value="/update_sample", method = RequestMethod.GET)
    public String updateSample(@RequestParam Map<String, String> param, Locale locale, Model model) {
        //로직 수행
        logger.debug("1.updateSample() {}", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        logger.debug("2.ServerTime is " + formattedDate);

        //STEP-01 : 저장
        String eno = param.get("eno");
        int i = Integer.parseInt(eno);
        Sample sample = sampleService.findById(i);

        //model객체를 이용해서 view로 Data전달
        model.addAttribute("serverTime", formattedDate);
        model.addAttribute("sample", sample);

        return "/sample/insert_sample";   //뷰 파일 리턴
    }


    @RequestMapping(value="/delete_sample", method = RequestMethod.GET)
    public String deleteSample(@RequestParam Map<String, String> param, Model model) {
        String eno = param.get("eno");
        int i = Integer.parseInt(eno);
        int count = sampleService.deleteById(i);

        return "/sample/get_list_object";   //뷰 파일 리턴
    }


    @RequestMapping(value="/delete_sample_ajax", method = RequestMethod.POST)
    @ResponseBody
    public Map deleteSampleAjax(@RequestBody Map<String, String> param, Model model) {
        String eno = param.get("eno");
        int i = Integer.parseInt(eno);
        int count = sampleService.deleteById(i);

        Map<String, String> result = new HashMap<>();
        if ( count == 1) {
            result.put("result", "OK");
            result.put("msg", "SUCCESS");
        } else {
            result.put("result", "NOT_OK");
            result.put("msg", "FAIL");
        }

        return result;
    }


}
