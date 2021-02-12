package com.wolox.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wolox.challenge.domain.Album;
import com.wolox.challenge.domain.AlbumPK;

@Repository
public interface AlbumRepository extends JpaRepository<Album, AlbumPK> {

	@Query(nativeQuery = true, value ="SELECT a.user_id FROM shared_album a where a.album_id = :albumId AND "
			+ "CASE :permission "
			+ "	WHEN 'R' THEN "
			+ "		a.read = true "
			+ "	WHEN 'W' THEN "
			+ "		a.write = true "
			+ "END " )
	List<String> getPermissionsUser(@Param("albumId") String albumId, @Param("permission") String permission);
}
