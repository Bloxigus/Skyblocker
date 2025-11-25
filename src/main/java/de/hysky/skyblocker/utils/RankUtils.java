package de.hysky.skyblocker.utils;

import net.hypixel.data.rank.MonthlyPackageRank;
import net.hypixel.data.rank.PackageRank;
import net.hypixel.data.rank.PlayerRank;

public class RankUtils {
	public static Object getEffectiveRank(PlayerRank rank, PackageRank boughtRank, MonthlyPackageRank monthlyRank) {
		if (rank == PlayerRank.NORMAL) {
			if (monthlyRank == MonthlyPackageRank.SUPERSTAR) {
				return monthlyRank;
			}
			return boughtRank;
		}
		return rank;
	}

	private static boolean isNotRank(Object rank) {
		return !(rank instanceof PlayerRank) && !(rank instanceof PackageRank) && !(rank instanceof MonthlyPackageRank);
	}

	private static int getRankWeight(Object rank) {
		return switch (rank) {
			case PlayerRank.NORMAL -> 0;
			case MonthlyPackageRank.NONE -> 1;
			case PackageRank.NONE -> 2;
			case PackageRank.VIP -> 3;
			case PackageRank.VIP_PLUS -> 4;
			case PackageRank.MVP -> 5;
			case PackageRank.MVP_PLUS -> 6;
			case MonthlyPackageRank.SUPERSTAR -> 7;
			case PlayerRank.YOUTUBER -> 8;
			case PlayerRank.GAME_MASTER -> 9;
			case PlayerRank.ADMIN -> 10;
			default -> -1;
		};
	}

	public static boolean rankCompare(Object firstRank, Object secondRank) {
		if (isNotRank(firstRank) || isNotRank(secondRank)) return false;

		return getRankWeight(firstRank) > getRankWeight(secondRank);
	}
}
