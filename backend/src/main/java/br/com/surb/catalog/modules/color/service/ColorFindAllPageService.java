package br.com.surb.catalog.modules.color.service;

import br.com.surb.catalog.modules.color.entity.Color;
import br.com.surb.catalog.modules.color.mapper.ColorMapper;
import br.com.surb.catalog.modules.color.repository.ColorRepository;
import br.com.surb.catalog.modules.color.response.ColorResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ColorFindAllPageService {
    private final ColorRepository colorRepository;

    public ColorFindAllPageService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @Transactional(readOnly = true)
    public Page<ColorResponse> execute(Pageable pageable) {
        Page<Color> colors = colorRepository.findAll(pageable);
        return colors.map((r) -> ColorMapper.toResponse(r));
    }
}
