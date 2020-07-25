package br.com.codenation.service;

import java.util.*;
import java.util.stream.Collectors;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

public class OrderServiceImpl implements OrderService {

	private ProductRepository productRepository = new ProductRepositoryImpl();

	/**
	 * Calculate the sum of all OrderItems
	 */
	@Override
	public Double calculateOrderValue(List<OrderItem> items) {

		Double itensSemDesconto = 0.0, itensComDesconto = 0.0;

		itensComDesconto = items.stream().filter(orderItem -> productRepository.findById(orderItem.getProductId()).get().getIsSale() == true ).
				reduce(0.0, (subtotal, orderItem) -> subtotal +
								((orderItem.getQuantity() * productRepository.findById(orderItem.getProductId()).get().getValue()) -
										0.2*(orderItem.getQuantity() * productRepository.findById(orderItem.getProductId()).get().getValue()))
						, Double::sum);

		itensSemDesconto = items.stream().filter(orderItem -> productRepository.findById(orderItem.getProductId()).get().getIsSale() == false ).
				reduce(0.0, (subtotal, orderItem) -> subtotal +
						(orderItem.getQuantity() * productRepository.findById(orderItem.getProductId()).get().getValue()), Double::sum);

		return itensSemDesconto + itensComDesconto;

	}

	/**
	 * Map from idProduct List to Product Set
	 */
	@Override
	public Set<Product> findProductsById(List<Long> ids) {

		Set<Product> products = ids.stream().map(aLong -> {
			try {
				return productRepository.findById(aLong).get();
			}catch (Exception e){
				return null;
			}
		}).filter(product -> product != null).collect(Collectors.toSet());

		return products;

	}

	/**
	 * Calculate the sum of all Orders(List<OrderIten>)
	 */
	@Override
	public Double calculateMultipleOrders(List<List<OrderItem>> orders) {

		return orders.stream().reduce(0.0, (subtotal, orderItem) -> subtotal + calculateOrderValue(orderItem), Double::sum);

	}

	/**
	 * Group products using isSale attribute as the map key
	 */
	@Override
	public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {

		return findProductsById(productIds).stream().collect(
				Collectors.groupingBy(Product::getIsSale));

	}

}