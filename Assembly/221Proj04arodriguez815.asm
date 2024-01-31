INCLUDE Irvine32.inc
INCLUDE Macros.inc

  
 .data

  source BYTE 64 DUP('#'),0
  destination  BYTE LENGTHOF source DUP(?),0
  actual_length DWORD ?
  ask BYTE "Enter a String: ",0

 .code
 main proc
    mov edx, OFFSET ask                    ; ask user for input
    call WriteString                       ; read string from keyboard 
    mov edx, OFFSET source                 ; where to put string from keyboard 
    mov ecx, SIZEOF source                 ; max num char to get (dont' over fill) 
    call ReadString                        ; get string 
    mov actual_length, eax                 ; how long is the string   
    call WriteString                       ; print inputed string 
    call crlf                              ; new line

    mov eax, 0                             ; zero out the eax register
    mov esi, 0                             ; set to zero to get first char
    mov edi, actual_length                 ; set the final char of source to the first char of destination
    dec edi                                ; get rid of null space
    mov ecx, SIZEOF source                

    L1:
        mov al, [source+esi]               ; move first char to al
        mov [destination+edi], al          ; al to the last of edi 
        add esi, TYPE source               ; move to the next char
        sub edi, TYPE destination          ; move to the next char
        loop L1

    mov edx, OFFSET destination            
    call WriteString                       ; print string
   

    exit
 main ENDP

END main
