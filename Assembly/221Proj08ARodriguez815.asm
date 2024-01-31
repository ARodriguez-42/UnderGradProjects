; csc 221-01
; project 8
; Turn entered string to all CAPS and numbers to binary value
; Antonio Rodriguez
; 11/19/20

INCLUDE Irvine32.inc
INCLUDE Macros.inc

MAX = 64
.data
    ask BYTE "Enter a string: ", 0
    source BYTE MAX DUP('#'),0

.code
main PROC
    mov edx, OFFSET ask             ; ask question
    call WriteString                ; print question
    mov edx, OFFSET source          ; where string will be stored
    mov ecx, LENGTHOF source        ; set counter to number of char's in string
    call ReadString                 ; get string
    call ToUpper                    ; call PROC to return upper case string
	call NumAndPrint
    exit
main ENDP

; Description: Convert each char in given buffer to upper case
; Receive: edx – address of buffer
; ecx – number of char in buffer to convert (NOT MAX)
; Returns: edx and ecx are returned to original values
; Requires: buffer to be MAX + 1 in length
ToUpper PROC uses EAX ECX EDX
    mov ecx, eax
    TOP:
        mov al, [edx]           ; move char to al register to compare
        cmp al, 'a'             ; if below 'a' it is NOT a (lower case letter)
        jb SKIP                 ; jump past conversion 
        cmp al, 'z'             ; if above 'z' it is NOT a (lower case letter) 
        ja SKIP                 ; jump past conversion
        and al, 11011111b       ; capitalize char
        mov [edx], al           ; replace lower case with upper cased char 
        SKIP:
        inc edx                 ; move to next char

    loop TOP
    ret
 ToUpper ENDP
 
 NumAndPrint PROC uses EAX EBX ECX EDX
    mov ecx, eax
	mov eax, 0
	mov ebx, TYPE BYTE
	L1:
		mov al,[edx]            ; move char into al register 
		cmp al, '0'             ; if below 0 it's not a digit
		jb SKIP                 ; jump past
		cmp al, '9'             ; if above 9 it's not a digit
		ja SKIP                 ; jump past
		and al, 00001111b       ; set 4 left most numbers to zero for correct binary display
		Call WriteBinB          ; write binary value
		jmp last                ; jump past WriteChar
		SKIP:
		call WriteChar          ; write char 
		last:                   
		call CRLF               ; new line
		inc edx                 ; move to the next char position 
	loop L1	
	ret
NumAndPrint ENDP
		
END main