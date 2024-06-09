package com.stargi.backend.records.application.internal;

import com.stargi.backend.iam.application.internal.events.ApplicationReadyEventHandler;
import com.stargi.backend.iam.infrastructure.IUserRepository;
import com.stargi.backend.records.domain.commands.CreateInfoCommand;
import com.stargi.backend.records.domain.commands.DeleteInfoByIdCommand;
import com.stargi.backend.records.domain.commands.EditInfoCommand;
import com.stargi.backend.records.domain.entities.Info;
import com.stargi.backend.records.domain.entities.InfoRecord;
import com.stargi.backend.records.domain.enums.Stage;
import com.stargi.backend.records.domain.services.IInfoCommandService;
import com.stargi.backend.records.infrastructure.persistence.InfoRecordRepository;
import com.stargi.backend.records.infrastructure.persistence.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InfoCommandService implements IInfoCommandService {

    private static final Logger LOGGER= LoggerFactory.getLogger(ApplicationReadyEventHandler.class);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final InfoRepository infoRepository;
    private final IUserRepository userRepository;
    private final InfoRecordRepository infoRecordRepository;
    @Override
    public Optional<Info> handle(CreateInfoCommand command) {
        var user=userRepository.findById(command.userId());
        var salesManager = userRepository.findById(command.salesManagerId());
        var executive = userRepository.findById(command.executiveId());

        if (salesManager.isEmpty()) throw new RuntimeException("Gerente de ventas no existe");
        if (executive.isEmpty()) throw new RuntimeException("Ejecutivo no existe");
        if(user.isEmpty()) throw new RuntimeException("Usuario no existe");
        LOGGER.info(command.toString());
        Info newInfo = new Info(
                command.ruc(),
                command.businessName(),
                command.country(),
                Stage.valueOf(command.stage()),
                command.commentary(),
                command.oppNumber(),
                command.product(),
                command.units(),
                command.realRent(),
                command.contact(),
                command.contactNumber(),
                command.email(),
                LocalDate.parse(command.expirationAt(),formatter),
                user.get(),
                LocalDate.parse(command.updatedAt(),formatter),
                LocalDate.parse(command.closeAt(),formatter),
                salesManager.get(),
                executive.get()
        );
        LOGGER.info(newInfo.toString());
        user.get().addInfo(newInfo);
        userRepository.save(user.get());

        var infoSaved=infoRepository.save(newInfo);
        InfoRecord newRecord= new InfoRecord(infoSaved.getUpdatedAt(),infoSaved,infoSaved.getStage().name(),infoSaved.getCommentary());
        infoRecordRepository.save(newRecord);
        return Optional.of(newInfo);
    }

    @Override
    public Long handle(DeleteInfoByIdCommand command) {
        var info=this.infoRepository.findById(command.infoId());
        if(info.isEmpty()) return 0L;
        infoRepository.delete(info.get());
        return 1L;
    }
    private InfoRecord createInfoRecord(Info info){
        return new InfoRecord(info.getUpdatedAt(),info,info.getStage().name(),info.getCommentary());
    }

    @Override
    public Optional<Info> handle(EditInfoCommand command) {
        var info=this.infoRepository.findById(command.infoId());
        if(info.isEmpty()) return Optional.empty();
        info.get().setInfo(command.newStage(), command.commentary(), LocalDate.parse(command.expirationAt(),formatter),LocalDate.parse(command.closetAt(),formatter));
        var newRecord=createInfoRecord(this.infoRepository.save(info.get()));
        this.infoRecordRepository.save(newRecord);
        return this.infoRepository.findById(command.infoId());
    }
}
