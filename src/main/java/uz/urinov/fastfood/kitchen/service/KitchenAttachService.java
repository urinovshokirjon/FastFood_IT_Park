package uz.urinov.fastfood.kitchen.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.urinov.fastfood.auth.enums.Language;
import uz.urinov.fastfood.kitchen.entity.KitchenAttachEntity;
import uz.urinov.fastfood.kitchen.repository.KitchenAttachRepository;
import uz.urinov.fastfood.util.SecurityUtil;


import java.util.List;

@Service
@RequiredArgsConstructor
public class KitchenAttachService {

    private final KitchenAttachRepository kitchenAttachRepository;

    public void kitchenAttachSave(List<String> attachList, Integer stadiumId, Language lang) {

        for (String attach : attachList) {
            KitchenAttachEntity entity=new KitchenAttachEntity();
            entity.setKitchenId(stadiumId);
            entity.setAttachId(attach);
            entity.setOwnerId(SecurityUtil.getProfileId());
            kitchenAttachRepository.save(entity);
        }

    }



}