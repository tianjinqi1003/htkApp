package com.htkapp.core.utils;

import com.htkapp.modules.merchant.shop.entity.Shop;

import java.util.Comparator;

/**
 * 距离比较器
 */
public class ShopDistanceComparator implements Comparator<Shop> {
	@Override
	public int compare(Shop c1, Shop c2)
	{
		return c1.getWithDistance() > c2.getWithDistance() ? 0 : -1;
	}
}