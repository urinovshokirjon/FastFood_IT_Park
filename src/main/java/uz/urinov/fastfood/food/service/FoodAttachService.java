package uz.urinov.fastfood.food.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import uz.urinov.fastfood.auth.enums.Language;
import uz.urinov.fastfood.food.entity.FoodAttachEntity;
import uz.urinov.fastfood.food.entity.FoodEntity;
import uz.urinov.fastfood.food.repository.FoodAttachRepository;
import uz.urinov.fastfood.food.repository.FoodRepository;
import uz.urinov.fastfood.kitchen.entity.KitchenEntity;
import uz.urinov.fastfood.kitchen.repository.KitchenRepository;
import uz.urinov.fastfood.util.SecurityUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodAttachService {
    @Autowired
    private ResourceBundleMessageSource rbms;
    private final FoodAttachRepository foodAttachRepository;
    private final KitchenRepository kitchenRepository;
    private final FoodRepository foodRepository;


    public void foodAttachSave(List<String> attachList, FoodEntity food, KitchenEntity kitchen, Language lang) {

        for (String attachId : attachList) {
            FoodAttachEntity entity = new FoodAttachEntity();
            entity.setFoodId(food.getId());
            entity.setAttachId(attachId);
            entity.setOwnerId(SecurityUtil.getProfileId());
            foodAttachRepository.save(entity);
        }
        food.setVisible(true);
        foodRepository.save(food);

        if (kitchen.getVisible().equals(false)) {
            kitchen.setVisible(true);
            kitchenRepository.save(kitchen);
        }


    }

}
