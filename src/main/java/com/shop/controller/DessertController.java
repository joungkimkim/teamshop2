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

    @GetMapping(value = {"/bread", "/bread/{page}"})
    public String bread(ItemSearchDto itemSearchDto, Principal principal, Model model,@PathVariable("page") Optional<Integer> page){
        if (itemSearchDto.getSearchDessertType() == null) {
            itemSearchDto.setSearchDessertType(Dessert.BREAD);
        }
        if(itemSearchDto.getSearchQuery() == null)
        {
            itemSearchDto.setSearchQuery("");
        }
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
        Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable);
        String name = memberService.loadMemberName(principal,httpSession);
        model.addAttribute("bread",Dessert.BREAD);
        model.addAttribute("name",name);
        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto",itemSearchDto);
        model.addAttribute("maxPage",5);
        return "/food/bread";
    }
    @GetMapping(value = {"/cake", "/cake/{page}"})
    public String cake(ItemSearchDto itemSearchDto, Principal principal, Model model,@PathVariable("page") Optional<Integer> page){
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

}
