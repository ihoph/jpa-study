package study.jpastudy.subway.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StationRepositoryTest {
    @Autowired
    private StationRepository stationRepository;

    @Test
    void save() {
        Station expected = new Station("잠실역");
        Station actual = stationRepository.save(expected);
        assertAll(
                () -> assertThat(actual.getId()).isNotNull(),
                () -> assertThat(actual.getName()).isEqualTo(expected.getName())
        );
    }

    @Test
    void findByName() {
        Station expected = new Station("잠실역");
        stationRepository.save(expected);

        Station actual = stationRepository.findByName("잠실역");
        assertThat(actual).isNotNull();
    }

    @Test
    void identity() {
        Station expected = new Station("잠실역");
        stationRepository.save(expected);
        Station actual = stationRepository.findById(expected.getId()).get();
        assertThat(actual == expected).isTrue();
    }

    @Test
    void update() {
        Station station = stationRepository.save(new Station("잠실역"));
        station.changeName("몽촌토성역");
//        stationRepository.flush();
        Station actual = stationRepository.findByName("몽촌토성역");
        assertThat(actual).isNotNull();
    }
}