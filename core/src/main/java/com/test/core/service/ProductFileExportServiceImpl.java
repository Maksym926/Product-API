package com.test.core.service;

import com.maksym.csv.CSVWriter;
import com.maksym.csv.CSVWriterImpl;
import com.test.core.repo.ProductRepo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ProductFileExportServiceImpl implements ProductFileExportService {
    private final ProductRepo productRepo;
    private final CSVWriter csvWriter = new CSVWriterImpl();

    public ProductFileExportServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public void exportProducts(String filePath) {
        var products = productRepo.findAllProducts(Integer.MAX_VALUE, 0);
        try {
            Path parent = Path.of(filePath).toAbsolutePath().getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            csvWriter.write(
                    products.stream(),
                    p -> new String[]{
                            p.getId(),
                            p.getName(),
                            p.getDescription(),
                            p.getBrand(),
                            p.getCategory(),
                            String.valueOf(p.getPrice()),
                            String.valueOf(p.getProductAvailable()),
                            String.valueOf(p.getStockQuantity())
                    },
                    false,
                    filePath
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to export products to CSV", e);
        }
    }
}
