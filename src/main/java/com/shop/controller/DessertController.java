package com.shop.controller;
import com.shop.constant.Dessert;
import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
import com.shop.service.ItemService;
import com.shop.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;
import java.util.Optional;


@Controller
@RequestMapping("dessert")
@RequiredArgsConstructor
public class DessertController {
    private final MemberService memberService;
    private final HttpSession httpSession;
    private final ItemService itemService;

    @GetMapping(value = {"/baggle", "/baggle/{page}"})
    public String baggle(ItemSearchDto itemSearchDto, Principal principal, Model model,@PathVariable("page") Optional<Integer> page){
        if (itemSearchDto.getSearchDessertType() == null) {
            itemSearchDto.setSearchDessertType(Dessert.BAGGLE);
        }
        if(itemSearchDto.getSearchQuery() == null)
        {
            itemSearchDto.setSearchQuery("");
        }
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
        Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable);
        String name = memberService.loadMemberName(principal,httpSession);
        model.addAttribute("baggle",Dessert.BAGGLE);
        model.addAttribute("name",name);
        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto",itemSearchDto);
        model.addAttribute("maxPage",5);
        return "/food/baggle";
    }
    @GetMapping(value = {"/sccon", "/sccon/{page}"})
    public String sccon(ItemSearchDto itemSearchDto, Principal principal, Model model,@PathVariable("page") Optional<Integer> page){
        if (itemSearchDto.getSearchDessertType() == null) {
            itemSearchDto.setSearchDessertType(Dessert.SCOON);

        }

        if(itemSearchDto.getSearchQuery() == null)
        {
            itemSearchDto.setSearchQuery("");
        }
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
        Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable);
        String name = memberService.loadMemberName(principal,httpSession);
        model.addAttribute("name",name);
        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto",itemSearchDto);
        model.addAttribute("maxPage",5);
        return "/food/sccon";
    }
    @GetMapping(value = {"/yogert", "/yogert/{page}"})
    public String yogert(ItemSearchDto itemSearchDto, Principal principal, Model model,@PathVariable("page") Optional<Integer> page){
        if (itemSearchDto.getSearchDessertType() == null) {
            itemSearchDto.setSearchDessertType(Dessert.YOGERT);
        }
        if(itemSearchDto.getSearchQuery() == null)
        {
            itemSearchDto.setSearchQuery("");
        }
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
        Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable);
        String name = memberService.loadMemberName(principal,httpSession);
        model.addAttribute("name",name);
        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto",itemSearchDto);
        model.addAttribute("maxPage",5);
        return "/food/yogert";
    }

    @GetMapping(value = {"/icecream", "/icecream/{page}"})
    public String iceCream(ItemSearchDto itemSearchDto, Principal principal, Model model, @PathVariable("page")Optional<Integer> page){

        if (itemSearchDto.getSearchDessertType() == null) {
            itemSearchDto.setSearchDessertType(Dessert.ICECREAM);
        }

        if(itemSearchDto.getSearchQuery() == null)
        {
            itemSearchDto.setSearchQuery("");
        }

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
        Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable);
        String name = memberService.loadMemberName(principal,httpSession);

        model.addAttribute("name",name);
        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto",itemSearchDto);
        model.addAttribute("maxPage",5);

        return "/food/icecream";
    }
    @GetMapping(value = {"/salad", "/salad/{page}"})
    public String salad(ItemSearchDto itemSearchDto, Principal principal, Model model,@PathVariable("page") Optional<Integer> page){
        if (itemSearchDto.getSearchDessertType() == null) {
            itemSearchDto.setSearchDessertType(Dessert.SALAD);
        }
        if(itemSearchDto.getSearchQuery() == null)
        {
            itemSearchDto.setSearchQuery("");
        }
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
        Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable);
        String name = memberService.loadMemberName(principal,httpSession);
        model.addAttribute("name",name);
        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto",itemSearchDto);
        model.addAttribute("maxPage",5);
        return "/food/salad";
    }
    @GetMapping(value = {"/cake", "/cake/{page}"})
    public String desert(ItemSearchDto itemSearchDto, Principal principal, Model model,@PathVariable("page") Optional<Integer> page){
        if (itemSearchDto.getSearchDessertType() == null) {
            itemSearchDto.setSearchDessertType(Dessert.CAKE);
        }
        if(itemSearchDto.getSearchQuery() == null)
        {
            itemSearchDto.setSearchQuery("");
        }
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
        Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable);
        String name = memberService.loadMemberName(principal,httpSession);
        model.addAttribute("name",name);
        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto",itemSearchDto);
        model.addAttribute("maxPage",5);
        return "/food/cake";
    }

}
