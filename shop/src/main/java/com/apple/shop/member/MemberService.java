package com.apple.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void saveMember(String username, String password, String displayName){
        try{
            if(username.length()>=3 && password.length()>=3){
                Member member = new Member();
                member.setUsername(username);
                member.setPassword(password);
                member.setDisplayName(displayName);
                memberRepository.save(member);
            }else{
                throw new IllegalArgumentException("id, pwd는 3자 이상");
            } // hash된 비밀번호가 넘어오기 때문에 pwd는 거르고있지 못함
        }catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id, pwd는 3자 이상");
        }catch (Exception e){
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미존재하는아이디");
        }

    }

}
