; csc 221-01
; project 5
; Reverse String with Push and Pop
; Antonio Rodriguez
; 10/22/20

INCLUDE Irvine32.inc
INCLUDE Macros.inc

  MAX = 64
 .data

  source BYTE MAX DUP('#'),0
  destination  BYTE LENGTHOF source DUP(?),0
  actual_length DWORD ?
  ask BYTE "Enter a String: ",0

 .code
 main proc
    mov edx, OFFSET ask                    ; ask user for input
    call WriteString                       ; read string from keyboard 
    mov edx, OFFSET source                 ; where to put string from keyboard 
    mov ecx, LENGTHOF source                 ; max num char to get (dont' over fill) 
    call ReadString                        ; get string 
    
    mov ecx, eax                           ; how long
    mov edi, OFFSET destination
    call revstr                       ; print string
    mov edx, OFFSET destination
    call WriteString
    exit
main ENDP

revstr PROC USES EAX EBX ECX EDX EDI
    mov ebx, ecx
    L1:
        mov al, [edx]               ; move first char to al
        push ax
        inc edx
    loop L1

    mov ecx, ebx
    L2:
        pop ax
        mov [edi], al          ; al to the last of edi  
        inc edi                ; move to the next char
    loop L2

    mov [edi], BYTE PTR 0
    ret
revstr ENDP

END main