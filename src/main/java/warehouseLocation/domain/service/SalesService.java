//package warehouseLocation.domain.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//public class SalesService {
//
//        private final ShopOrderRepository shopOrderRepository;
//        private final ItemRepository itemRepository;
//        private final StoreRepository storeRepository;
//        private final ItemMajorCategoryRepository itemMajorCategoryRepository;
//        private final ItemMiddleCategoryRepository itemMiddleCategoryRepository;
//        private final ItemSubCategoryRepository itemSubCategoryRepository;
//        private final JPAQueryFactory jpaQueryFactory;
//
//        @Override
//        public List<ItemMajorCategoryForm> itemRevenue(Integer storeCode, Date startDate, Date endDate) {
//            QShopOrder shopOrder = QShopOrder.shopOrder; // Order 테이블
//            QItem item = QItem.item; // Item 테이블
//            QStore store = QStore.store; // Store 테이블
//
//            // 1. 가맹점 및 날짜 조건에 맞는 Item 정보 조회
//            List<Tuple> orderList = jpaQueryFactory
//                    .select( item.itemCode, item.itemName, item.itemPrice, item.itemCapacity,
//                            item.itemUnitQuantity, item.itemUnit, item.itemStandard, item.itemStorage,
//                            item.itemCountryOrigin, item.itemMajorCategory.itemCategoryNum,
//                            item.itemMiddleCategory.itemCategoryNum, item.itemSubCategory.itemCategoryNum,
//                            item.itemImageFile.fileNum)
//                    .from(shopOrder)
//                    .innerJoin(shopOrder.storeO, QStore.store)
//                    .innerJoin(shopOrder.itemO, QItem.item)
//                    .where(shopOrder.orderDate.between(startDate, endDate),
//                            shopOrder.storeO.storeCode.eq(storeCode))
//                    .fetch();
//
//            // 2. ItemDto로 변환
//            List<ItemDto> itemList = orderList.stream()
//                    .map(order -> {
//                        ItemDto itemDto = new ItemDto();
//                        itemDto.setItemCode(order.get(item.itemCode));
//                        itemDto.setItemName(order.get(item.itemName));
//                        itemDto.setItemPrice(order.get(item.itemPrice));
//                        itemDto.setItemCapacity(order.get(item.itemCapacity));
//                        itemDto.setItemUnitQuantity(order.get(item.itemUnitQuantity));
//                        itemDto.setItemUnit(order.get(item.itemUnit));
//                        itemDto.setItemStandard(order.get(item.itemStandard));
//                        itemDto.setItemStorage(order.get(item.itemStorage));
//                        itemDto.setItemCountryOrigin(order.get(item.itemCountryOrigin));
//                        itemDto.setItemFileNum(order.get(item.itemImageFile.fileNum));
//                        itemDto.setItemMajorCategoryNum(order.get(item.itemMajorCategory.itemCategoryNum));
//                        itemDto.setItemMiddleCategoryNum(order.get(item.itemMiddleCategory.itemCategoryNum));
//                        itemDto.setItemSubCategoryNum(order.get(item.itemSubCategory.itemCategoryNum));
//                        return itemDto;
//                    })
//                    .collect(Collectors.toList());
//
//            // 3. 대분류 기준으로 그룹화
//            Map<Integer, ItemMajorCategoryForm> majorCategoryMap = new HashMap<>();
//
//            for (ItemDto itemDto : itemList) {
//                Integer majorCategoryNum = itemDto.getItemMajorCategoryNum();
//
//                // 대분류 생성
//                ItemMajorCategoryForm majorCategory = majorCategoryMap.computeIfAbsent(majorCategoryNum, key -> {
//                    Optional<ItemMajorCategory> majorCategoryOpt = itemMajorCategoryRepository.findById(key);
//                    if (majorCategoryOpt.isEmpty()) return null;
//
//                    ItemMajorCategory category = majorCategoryOpt.get();
//                    return ItemMajorCategoryForm.builder()
//                            .itemMajorCategoryNum(key)
//                            .itemMajorCategoryName(category.getItemCategoryName())
//                            .itemList(new ArrayList<>())
//                            .midCategories(new ArrayList<>())
//                            .build();
//                });
//
//                if (majorCategory == null) continue;
//
//                // 중분류 추가
//                Integer middleCategoryNum = itemDto.getItemMiddleCategoryNum();
//
//
//                ItemMiddleCategoryForm middleCategory = majorCategory
//                        .getMidCategories().stream()
//                        .filter(mid -> mid.getItemMiddleCategoryNum().equals(middleCategoryNum))
//                        .findFirst()
//                        .orElseGet(() -> {
//                            Optional<ItemMiddleCategory> middleCategoryOpt = itemMiddleCategoryRepository.findById(middleCategoryNum);
//                            if (middleCategoryOpt.isEmpty()) return null;
//
//                            ItemMiddleCategory midCategory = middleCategoryOpt.get();
//                            ItemMiddleCategoryForm midForm = ItemMiddleCategoryForm.builder()
//                                    .itemMiddleCategoryNum(middleCategoryNum)
//                                    .itemMiddleCategoryName(midCategory.getItemCategoryName())
////                                .middleCategoryName(midCategory.getItemCategoryName())
//                                    .subCategories(new ArrayList<>())
//                                    .build();
//
//                            // 중분류 처리
////                        ItemMiddleCategoryForm middleCategory = majorCategory
////                        .getMidCategories().stream()
////                                .filter(mid -> mid.getItemMiddleCategoryNum().equals(itemDto.getItemMiddleCategoryNum()))
////                                .findFirst()
////                                .orElseGet(() -> {
////                                    Optional<ItemMiddleCategory> middleCategoryOpt = itemMiddleCategoryRepository.findById(middleCategoryNum);
////                                    if (middleCategoryOpt.isEmpty()) return null;
////
////                                    ItemMiddleCategory midCategory = middleCategoryOpt.get();
////                                    // 중분류 객체 생성 시 이름 설정
////                                    return ItemMiddleCategoryForm.builder()
////                                            .itemMiddleCategoryNum(middleCategoryNum)
////                                            .itemMiddleCategoryName(midCategory.getItemCategoryName())  // 중분류 이름 설정
////                                            .subCategories(new ArrayList<>())
////                                            .build();
////                                });
//
//
//                            majorCategory
//                                    .getMidCategories().add(midForm);
////                                .getMiddleCategories().add(midForm);
//                            return midForm;
//                        });
//
//                if (middleCategory == null) continue;
//
//                // 소분류 추가
//                Integer subCategoryNum = itemDto.getItemSubCategoryNum();
//                ItemSubCategoryForm subCategory = middleCategory.getSubCategories().stream()
//                        .filter(sub -> sub.getItemSubCategoryNum().equals(subCategoryNum))
//                        .findFirst()
//                        .orElseGet(() -> {
//                            Optional<ItemSubCategory> subCategoryOpt = itemSubCategoryRepository.findById(subCategoryNum);
//                            if (subCategoryOpt.isEmpty()) return null;
//
//                            ItemSubCategory subCat = subCategoryOpt.get();
//                            ItemSubCategoryForm subForm = ItemSubCategoryForm.builder()
//                                    .itemSubCategoryNum(subCategoryNum)
//                                    .itemSubCategoryName(subCat.getItemCategoryName())
////                                .subCategoryName(subCat.getItemCategoryName())
//                                    .build();
//                            middleCategory.getSubCategories().add(subForm);
//                            return subForm;
//                        });
//
//                // 최종적으로 아이템 추가
//                majorCategory.getItemList().add(itemDto);
//            }
//
//            // 4. 결과 반환
//            return new ArrayList<>(majorCategoryMap.values());
//        }}
//}
