package com.example.gururu_be.service.store;

import com.example.gururu_be.domain.dto.store.ProductDto;
import com.example.gururu_be.domain.entity.store.Product;
import com.example.gururu_be.domain.entity.store.Store;
import com.example.gururu_be.domain.repository.store.StoreRepository;
import com.example.gururu_be.domain.repository.store.product.ProductRepository;
import com.example.gururu_be.enumerate.StatusFlag;
import com.example.gururu_be.util.exception.ErrorCode;
import com.example.gururu_be.util.exception.RequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    
    /**
     * M2-10 가격 등록
     */
    @Transactional
    public ProductDto createProduct(UUID storeRegisterId, ProductDto productDto) {
        Optional<Store> optionalStore = storeRepository.findById(storeRegisterId);
        Store store = optionalStore.orElseThrow(() -> new RequestException(ErrorCode.STORE_NOT_FOUND_404));

        Product product = Product.builder()
                .store(store)
                .sizeName(productDto.getSizeName())
                .beautyName(productDto.getBeautyName())
                .beautyDesc(productDto.getBeautyDesc())
                .amount(productDto.getAmount())
                .build();
        // 7. 새로 생성한 객체를 Repository 를 이용하여 DB에 저장한다
        return new ProductDto(productRepository.save(product));
    }

    /**
     * M2-11 선택 가격 조회
     */
    public ProductDto getOneProduct(UUID storeRegisterId,UUID productId) {
        storeRepository.findById(storeRegisterId).orElseThrow(
                () -> new RequestException(ErrorCode.STORE_NOT_FOUND_404));
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Product product = optionalProduct.orElseThrow(
                () -> new RequestException(ErrorCode.BEAUTICIAN_NOT_FOUND_404));
        if (optionalProduct.get().getDelFlag().equals(StatusFlag.DELETED)) {
            throw new RequestException(ErrorCode.BEAUTICIAN_DELETE_409);
        }
        return new ProductDto(product);
    }
    
    /**
     * M2-12 가격 정보 수정
     */
    @Transactional
    public void modifyProduct(UUID storeRegisterId,UUID productId, ProductDto productDto) {
        storeRepository.findById(storeRegisterId).orElseThrow(
                () -> new RequestException(ErrorCode.STORE_NOT_FOUND_404));
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Product product = optionalProduct.orElseThrow(
                () -> new RequestException(ErrorCode.BEAUTICIAN_NOT_FOUND_404));
        if (optionalProduct.get().getDelFlag().equals(StatusFlag.DELETED)) {
            throw new RequestException(ErrorCode.BEAUTICIAN_DELETE_409);
        }
        product.updateProduct(productDto);
    }
    
    /**
     * M2-13 가격 삭제
     */
    @Transactional
    public void deleteProduct(UUID storeRegisterId, UUID productId) {
        storeRepository.findById(storeRegisterId).orElseThrow(
                () -> new RequestException(ErrorCode.STORE_NOT_FOUND_404));
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Product product = optionalProduct.orElseThrow(
                () -> new RequestException(ErrorCode.BEAUTICIAN_NOT_FOUND_404));
        if (optionalProduct.get().getDelFlag().equals(StatusFlag.DELETED)) {
            throw new RequestException(ErrorCode.BEAUTICIAN_DELETE_409);
        }
        product.delete();
    }
    
    /**
     * M2-14 가격 전체 조회
     */
    public List<ProductDto> getAllProduct(UUID storeRegisterId) {
        storeRepository.findById(storeRegisterId)
                .orElseThrow(() -> new RequestException(ErrorCode.STORE_NOT_FOUND_404));
        return productRepository.findAllProductBystoreRegisterId_DSL(storeRegisterId);
    }
}
